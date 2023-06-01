package com.example.cokkiri.service;

import com.example.cokkiri.model.*;
import com.example.cokkiri.repository.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Transactional
@Service("matching")
public class MatchingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MatchingService.class);
    // 싱글스레드 호출
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    // 수업 레포지토리
    @Autowired
    private ClassMatchedListRepository classMatchedListRepository;

    // 공강 레포지토리
    @Autowired
    private PublicMatchedListRepository publicMatchedListRepository;
    @Autowired
    private MatchingAgreeRepository matchingAgreeRepository;
    @Autowired
    private NoShowPublicMatchListRepository noShowPublicMatchListRepository;
    @Autowired
    private NoShowClassMatchListRepository noShowClassMatchRepository;
    @Autowired
    private AccusationRepository accusationRepository;
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  MatchingWaitRepository matchingWaitRepository;



    // 배열에 저장 (공강)
    List<PublicMatching> publicLectureUsers = new ArrayList<>();
    // 배열에 저장 (수업)
    List<ClassMatching> classLectureUsers = new ArrayList<>();
    //반환 배열
    List<PublicMatching> publicUsersList = new ArrayList<>();
    List<ClassMatching> classUserList =new ArrayList<>();



    @PostConstruct
    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                LOGGER.error(e.toString());
            }
        }));
    }

    // 요일, 시간, 희망인원이 같을 시 증가
    int userCount;
    //매치된 user들 지우는 용도
    List<Integer> usermatched = new ArrayList<>();


    // 겹치는 학수번호 확인
    public static Set<String> findDuplicatesCourse(List<String>List) {
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (String i: List) {
            if (!seen.add(i)) {
                duplicates.add(i);
            }
        }
        return duplicates;
    }


    // 겹치는 시간 확인 알고리즘
    public static List<LocalTime> findDuplicatTime(List<LocalTime>user , List<LocalTime>lastUser) {
        LocalTime userStartTime = user.get(0); // 비교할 user 매칭가능 시작시간
        LocalTime userEndTime = user.get(1);
        LocalTime lastUserStartTime = lastUser.get(0); // 마지막으로 배열에 들어온 user 매칭가능 시작시간
        LocalTime lastUserEndTime = lastUser.get(1);
        // 겹치는 시간 파악 후
        LocalTime startTime = null;
        LocalTime endTime = null;
        if(userStartTime.isAfter(lastUserStartTime)){
            if(lastUserEndTime.isBefore(userStartTime) || lastUserEndTime.equals(userStartTime)){
                System.out.println("두 user는 시간이 겹치지 않습니다.");
                startTime = null;
                endTime = null;
            }
            else if(userEndTime.isAfter(lastUserEndTime)){
                startTime = userStartTime;
                endTime = lastUserEndTime;
            }else if(userEndTime.isBefore(lastUserEndTime)){
                startTime = userStartTime;
                endTime = userEndTime;
            }else if(userEndTime.equals(lastUserEndTime)){
                startTime = userStartTime;
                endTime = lastUserEndTime;
            }
        }
        if(lastUserStartTime.isAfter(userStartTime)){
            if(userEndTime.isBefore(lastUserStartTime) || userEndTime.equals(lastUserStartTime)){
                System.out.println("두 user는 시간이 겹치지 않습니다.");
                startTime = null;
                endTime = null;
            }
            else if(lastUserEndTime.isAfter(userEndTime)){
                startTime = lastUserStartTime;
                endTime = userEndTime;
            }
            else if(lastUserEndTime.isBefore(userEndTime)){
                startTime = lastUserStartTime;
                endTime = lastUserEndTime;
            }
            else if(lastUserEndTime.equals(userEndTime)){
                startTime = lastUserStartTime;
                endTime = lastUserEndTime;
            }
        }
        if(userStartTime.equals(lastUserStartTime)){
            if(userEndTime.equals(lastUserEndTime)){
                startTime = userStartTime;
                endTime = userEndTime;
            }
            else if(userEndTime.isAfter(lastUserEndTime)){
                startTime = userStartTime;
                endTime = lastUserEndTime;
            }
            else if(userEndTime.isBefore(lastUserEndTime)){
                startTime = userStartTime;
                endTime = userEndTime;
            }
        }
        System.out.println("시작시간은 : " +startTime);
        System.out.println("끝 시간은 : " + endTime);
        List<LocalTime> times  = new ArrayList<>();
        times.add(startTime);
        times.add(endTime);
        return times;
    }

    // 매칭 대기 api
    public  MatchingWait savePublicMatchingWaitUser(List<PublicMatching>userList){
        MatchingWait waitUser = new MatchingWait();
        waitUser.setMatchingType(userList.get(userList.size()-1).getMatchingType());
        waitUser.setEmail(userList.get(userList.size()-1).getEmail());
        waitUser.setStatus("매칭 대기중");
        matchingWaitRepository.save(waitUser);
        return  waitUser;
    }

    public  MatchingWait saveClassMatchingWaitUser(List<ClassMatching>userList){
        MatchingWait waitUser = new MatchingWait();
        waitUser.setMatchingType(userList.get(userList.size()-1).getMatchingType());
        waitUser.setEmail(userList.get(userList.size()-1).getEmail());
        waitUser.setStatus("매칭 대기중");
        matchingWaitRepository.save(waitUser);
        return  waitUser;
    }

    // 매칭 대기중 유저 모두 반환
    public List<MatchingWait> findAllMatchingWait(){
        return matchingWaitRepository.findAll();
    }

    // 이메일로 매칭 대기중 반환
    public List<MatchingWait> findMatchingWaitByEmail(String id){
        return matchingWaitRepository.findByEmail(id);
    }
    public PublicMatchedList findPublicMatch(List<PublicMatching>userList , int count ) {
        // 객체 생성
        PublicMatchedList matched = new PublicMatchedList();
        if (userList.size() < 2) {
            savePublicMatchingWaitUser(userList);
            return null;
        } else {
            for (int i = 0; i <= userList.size() - 2; i++) {
                LocalTime UserStartDate = userList.get(i).getStartTime();
                LocalTime UserEndDate = userList.get(i).getEndTime();
                List<LocalTime> user = new ArrayList<>();
                user.add(UserStartDate);
                user.add(UserEndDate);
                System.out.println(user);

                LocalTime lastUserStartDate = userList.get(userList.size() - 1).getStartTime();
                LocalTime lastUserEndDate = userList.get(userList.size() - 1).getEndTime();
                List<LocalTime> lastUser = new ArrayList<>();
                lastUser.add(lastUserStartDate);
                lastUser.add(lastUserEndDate);
                System.out.println(lastUser);

                List<LocalTime> times = new ArrayList<>(findDuplicatTime(user, lastUser));

                //    마지막 요소와 시간요일,희망인원이 같은 요소있으면 배열 다 돌아
                boolean day = (userList.get(i).getAvailableDay()).equals(userList.get(userList.size() - 1).getAvailableDay());
                boolean head = (userList.get(i).getHeadCount()) == (userList.get(userList.size() - 1).getHeadCount());
                if (day && head && times != null) {



                    userCount += 1;
                    //요소를 찾았지만 희망인원이 채워 졌는지 묻는 조건문
                    if (userCount + 1 == count) {
                        PublicMatching userLast = userList.get(userList.size() - 1);

                        //  반환 배열에 넣음
                        publicUsersList.add(userLast);


                        for (int j = 0; j < userList.size() - 1; j++) {

                            LocalTime UserStartDates = userList.get(j).getStartTime();
                            LocalTime UserEndDates = userList.get(j).getEndTime();
                            List<LocalTime> users = new ArrayList<>();
                            users.add(UserStartDates);
                            users.add(UserEndDates);
                            System.out.println(users);

                            LocalTime lastUserStartDates = userList.get(userList.size() - 1).getStartTime();
                            LocalTime lastUserEndDates = userList.get(userList.size() - 1).getEndTime();
                            List<LocalTime> lastUsers = new ArrayList<>();
                            lastUsers.add(lastUserStartDates);
                            lastUsers.add(lastUserEndDates);
                            System.out.println(lastUsers);

                            List<LocalTime> timess = new ArrayList<>(findDuplicatTime(users, lastUsers));

                            boolean days = (userList.get(j).getAvailableDay()).equals(userList.get(userList.size() - 1).getAvailableDay());
                            boolean heads = (userList.get(j).getHeadCount()) == (userList.get(userList.size() - 1).getHeadCount());
                            if (days && heads && timess != null) {

                                // 겹치는 시간 확인
                                matched.setPromiseTime(timess);
                                publicUsersList.add(userList.get(j));
                                usermatched.add(j);

                            }
                        }
                        //마지막 요소 제거
                        userList.remove(userLast);
                        //매치 된 요소 제거
                        for (int k = 0; k < usermatched.size(); k++) {
                            userList.remove(usermatched.get(k));
                        }
                        userCount = 0;

                        // 학번 배열 생성, set
                        List<String> emailList = new ArrayList<>();
                        for (int k = 0; k <= publicUsersList.size() - 1; k++) {
                            String studentId = publicUsersList.get(k).getEmail();
                            emailList.add(studentId);
                        }
                        // 매칭된 학생들 학번 리스트
                        matched.setEmailList(emailList);
                        // 매칭 타입
                        matched.setMatchingType(userLast.getMatchingType());
                        // 매칭 가능한 요일
                        matched.setAvailableDay(userLast.getAvailableDay());
                        // 매칭 희망인원
                        matched.setHeadCount(userLast.getHeadCount());

                        matched.setMatchingRes("매칭중");

                        //매칭 시간 현재 날짜로 세팅
                        LocalDate date = LocalDate.now();
                        // 매칭 시간
                        matched.setMatchingTime(date);

                        publicUsersList.clear();

                        // entity 반환.
                        return matched;
                    }
                    //희망인원이 다 안채워 졌으면 continue
                    else {
                        continue;
                    }
                    //배열 내 요소가 다를 시,
                } else {
                    continue;
                }
            }
        }
        //끝까지 돌았는데 못찾았을 시
        userCount = 0;
        savePublicMatchingWaitUser(userList);
        return null;
    };

    //수업매칭
    public ClassMatchedList findClassMatch(List<ClassMatching>userList , int count ){
        // 객체 생성
        ClassMatchedList matched = new ClassMatchedList();
        if(userList.size()<2){
            saveClassMatchingWaitUser(userList);
            return null;
        }
        else{
            for(int i =0; i <= userList.size()-2; i++){
                List<String>firstUserCourseNum = userList.get(i).getCourseNumber();
                List<String>lastUserCourseNum = userList.get(userList.size()-1).getCourseNumber();
                List<String>courseNumList = new ArrayList<>();
                courseNumList.addAll(firstUserCourseNum);
                courseNumList.addAll(lastUserCourseNum);
                //시간표가 겹치는 유저 찾아
                List<String> courseNum = new ArrayList<>(findDuplicatesCourse(courseNumList));
                System.out.println("겹치는 수업은 : " + courseNum + " 입니다");
                // 마지막 요소와 시간요일,희망인원이 같은 요소있으면 배열 다 돌아
                boolean head = (userList.get(i).getHeadCount())==(userList.get(userList.size()-1).getHeadCount());
                if (head&&courseNum!=null) {

                    userCount+=1;
                    // 요소를 찾았지만 희망인원이 채워 졌는지 묻는 조건문
                    if(userCount+1==count){
                        ClassMatching userLast = userList.get(userList.size() - 1);
                        // 반환 배열에 넣음
                        classUserList.add(userLast);


                        for(int j = 0; j < userList.size()-1 ; j++){
                            List<String>firstUserCourseNumber = userList.get(j).getCourseNumber();
                            List<String>lastUserCourseNumber = userList.get(userList.size()-1).getCourseNumber();
                            List<String>courseNumberList = new ArrayList<>();
                            courseNumberList.addAll(firstUserCourseNumber);
                            courseNumberList.addAll(lastUserCourseNumber);
                            //시간표가 겹치는 유저 찾아
                            List<String> courseNumber = new ArrayList<>(findDuplicatesCourse(courseNumberList));
                            boolean heads = (userList.get(j).getHeadCount())==(userList.get(userList.size()-1).getHeadCount());
                            if (heads&&courseNumber!=null) {

                                // 겹치는 시간 확인
                                matched.setCourseNumber(courseNumber);

                                classUserList.add(userList.get(j));
                                usermatched.add(j);

                            }
                        }
                        //마지막 요소 제거
                        userList.remove(userLast);
                        //매치 된 요소 제거
                        for(int k=0 ;  k <  usermatched.size() ;k++){
                            userList.remove(usermatched.get(k));
                        }
                        userCount =0;

                        // 학번 배열 생성, set
                        List<String> emailList = new ArrayList<>();
                        for (int k = 0; k <= classUserList.size() - 1; k++) {
                            String email = classUserList.get(k).getEmail();
                            emailList.add(email);
                        }
                        // 매칭된 학생들 학번 리스트
                        matched.setEmailList(emailList);
                        // 매칭 타입
                        matched.setMatchingType(userLast.getMatchingType());

                        // 매칭 희망인원
                        matched.setHeadCount(userLast.getHeadCount());
                        matched.setMatchingRes("매칭중");

                        //매칭 시간 현재 날짜로 세팅
                        LocalDate date = LocalDate.now();

                        // 매칭 시간
                        matched.setMatchingTime(date);

                        classUserList.clear();

                        // entity 반환.
                        return matched;
                    }
                    //희망인원이 다 안채워 졌으면 continue
                    else{
                        continue;
                    }
                    // 배열 내 요소가 다를 시,
                }else{
                    continue;
                }
            }
            //끝까지 돌았는데 못찾았을 시
            userCount=0;
            saveClassMatchingWaitUser(userList);
            return null;
        }
    }


    public PublicMatchedList publicMatch(PublicMatching user){
        // 매칭된 사람 수 = 희망인원
        int count = user.getHeadCount();
        publicLectureUsers.add(user);
        PublicMatchedList publicMatchedList = new PublicMatchedList();

        String id = user.getEmail();
        Optional<User> userInfo = userRepository.findById(id);
        if(userInfo.get().isPublicMatching()==false){
            if(userInfo.get().getRestrctionDate()==null || userInfo.get().getRestrctionDate().isBefore(LocalDateTime.now())){
                publicMatchedList = findPublicMatch(publicLectureUsers, count);
            }else{
                LocalDateTime restrictionDate = userInfo.get().getRestrctionDate();
                String string = " : 매칭이 해당일자 까지 제한됩니다.";
                StringBuffer buffer = new StringBuffer(string);
                buffer.insert(0,restrictionDate);
                String str = buffer.toString();
                publicMatchedList.setMatchingRes(str);
            }
        }else{
            publicMatchedList.setMatchingRes("중복 매칭은 불가합니다.");
        }
        sendSSEtoPublicUser(publicMatchedList);
        savePublicUser(publicMatchedList);
        return publicMatchedList;
    }



    public ClassMatchedList classMatch(ClassMatching user){
        // 매칭된 사람 수 = 희망인원
        int count = user.getHeadCount();
        classLectureUsers.add(user);
        ClassMatchedList classMatchedList = new ClassMatchedList();
        String id = user.getEmail();
        Optional<User> userInfo = userRepository.findById(id);
        if(userInfo.get().isClassMatching()==false){
            // 유저의 제한날짜가 없거나 제한 날짜가 현재 날짜 보다 전에 있으면
            if(userInfo.get().getRestrctionDate()==null || userInfo.get().getRestrctionDate().isBefore(LocalDateTime.now())){
                userInfo.get().setClassMatching(true);
                classMatchedList = findClassMatch(classLectureUsers,count);
            }else{
                LocalDateTime restrictionDate = userInfo.get().getRestrctionDate();
                String string = " : 매칭이 해당일자 까지 제한됩니다.";
                StringBuffer buffer = new StringBuffer(string);
                buffer.insert(0,restrictionDate);
                String str = buffer.toString();
                classMatchedList.setMatchingRes(str);
            }
        }else{
            classMatchedList.setMatchingRes("중복 매칭은 불가합니다.");
        }
        sendSSEtoClassUser(classMatchedList);
        saveClassUser(classMatchedList);
        return classMatchedList;
    }

    //수업 매칭 전부 반환
    public List<ClassMatchedList> findAllClassMatching(){
        List<ClassMatchedList> matchedlist = new ArrayList<>();
        classMatchedListRepository.findAll().forEach(e->matchedlist.add(e));
        return matchedlist;
    }


    //수업 매칭 Id로 찾아서 반환
    public List<ClassMatchedList> findClassMatchingById(String id){
        return classMatchedListRepository.findByEmailListContains(id);
    }

    //공강 매칭 전부 반환
    public List<PublicMatchedList> findAllPublicMatching(){
        LocalDate nowDate = LocalDate.now();
        List<PublicMatchedList> matchedlist = new ArrayList<>();
        publicMatchedListRepository.findAll().forEach(e->matchedlist.add(e));

        return matchedlist;
    }

    //공강 매칭 Id로 찾아서 반환
    public List<PublicMatchedList> findPublicMatchingById(String id){
        return publicMatchedListRepository.findByEmailListContains(id);
    }

    public ClassMatchedList findClassMatchingByMatchId(int id){
        return classMatchedListRepository.findByMatchingId(id);
    }

    public PublicMatchedList findPublicMatchingByMatchId(int id){
        return publicMatchedListRepository.findByMatchingId(id);
    }

    // 매칭 타입별로 저장
    public ClassMatchedList saveClassUser(ClassMatchedList matchedList){
        for(int i = 0 ; i < matchedList.getEmailList().size() ; i++){
            String email = matchedList.getEmailList().get(i);
            Optional<User> user = userRepository.findById(email);
            user.get().setHeart((user.get().getHeart())-10); //하트 10개 차감
            userRepository.save(user.get());
            List<MatchingWait> waitUser = matchingWaitRepository.findByEmail(email);
            if(waitUser.get(i).getMatchingType()=="class"){
                matchingWaitRepository.delete(waitUser.get(i));
            }
        }
        return classMatchedListRepository.save(matchedList); // 데베에 저장

    };

    @Autowired
    SseService sseService;
    public void sendSSEtoClassUser(ClassMatchedList matchedList){
        List receiver = matchedList.getEmailList();
        String content = "매칭이 성사되었습니다.";
        String type = matchedList.getMatchingType();
        sseService.sendList(receiver,content,type);
    }

    public void sendSSEtoPublicUser(PublicMatchedList matchedList){
        List receiver = matchedList.getEmailList();
        String content = "매칭이 성사되었습니다.";
        String type = matchedList.getMatchingType();
        sseService.sendList(receiver,content,type);
    }
    public PublicMatchedList savePublicUser(PublicMatchedList matchedList){
        for(int i = 0 ; i < matchedList.getEmailList().size() ; i++){
            String email = matchedList.getEmailList().get(i);
            Optional<User> user = userRepository.findById(email);
            user.get().setHeart((user.get().getHeart())-10); //하트 10개 차감
            userRepository.save(user.get());
            List<MatchingWait> waitUser = matchingWaitRepository.findByEmail(email);
            if(waitUser.get(i).getMatchingType()=="free"){
                matchingWaitRepository.delete(waitUser.get(i));
            }
        }
        return publicMatchedListRepository.save(matchedList); // 데베에 저장
    };

    // 매치된 리스트에서 삭제
    public  String deletePublicUser(int id){
        PublicMatchedList list = publicMatchedListRepository.findByMatchingId(id);
        if (list != null) {
            publicMatchedListRepository.delete(list);
            return "삭제 되었습니다.";
        }else{
            return "해당 매칭 아이디에 맞는 리스트가 조회되지 않습니다.";
        }
    }

    public String deleteClassUser(int id){
        ClassMatchedList list = classMatchedListRepository.findByMatchingId(id);
        if (list != null) {
            classMatchedListRepository.delete(list);;
            return "삭제 되었습니다";
        }else{
            return "해당 매칭 아이디에 맞는 리스트가 조회되지 않습니다";
        }
    }

    // 매치 대기 상태에서 삭제
    public MatchingWait deletePublicMatchingWaitById(int id){
        MatchingWait waitUser = matchingWaitRepository.findById(id);
        String email = waitUser.getEmail();
        Optional<User> user = userRepository.findById(email);
        user.get().setHeart(user.get().getHeart()+10);                // 하트 반환
        for (int i = 0 ; i < publicLectureUsers.size() ; i ++){
            if(email == publicLectureUsers.get(i).getEmail()){
                publicLectureUsers.remove(publicLectureUsers.get(i)); // 배열에서 삭제
            }
        }
        userRepository.save(user.get());
        matchingWaitRepository.delete(waitUser);
        return  waitUser;
    }

    public MatchingWait deleteClassMatchingWaitById(int id){
        MatchingWait waitUser = matchingWaitRepository.findById(id);
        String email = waitUser.getEmail();
        Optional<User> user = userRepository.findById(email);
        user.get().setHeart(user.get().getHeart()+10);              // 하트 반환
        for (int i = 0 ; i < classLectureUsers.size() ; i ++){
            if(email == classLectureUsers.get(i).getEmail()){
                classLectureUsers.remove(classLectureUsers.get(i)); // 배열에서 삭제
            }
        }
        userRepository.save(user.get());
        matchingWaitRepository.delete(waitUser);
        return  waitUser;
    }


    public String publicMatchAgree(int matchingId , String id) {
        PublicMatchedList matchedList = publicMatchedListRepository.findByMatchingIdAndEmailListContains(matchingId , id);
        if(matchedList.getMatchingAgree() != matchedList.getHeadCount()) {
            matchedList.setMatchingAgree(matchedList.getMatchingAgree() + 1); // 1씩 증가
            String comment = " 매칭완료 버튼을 눌렀습니다";
            return comment;
        }else{
            matchedList.setMatchingRes("매칭완료");
            publicMatchedListRepository.save(matchedList);
            String comment = " 매칭인원이 충족되었습니다";
            return comment;
        }
    }

    public String classMatchAgree(int matchingId,String id) {
        ClassMatchedList matchedList = classMatchedListRepository.findByMatchingIdAndEmailListContains(matchingId,id);
        if(matchedList== null){
            return "해당 매치를 찾지 못했습니다.";
        }
        if(matchedList.getMatchingAgree() != matchedList.getHeadCount()) {
            matchedList.setMatchingAgree(matchedList.getMatchingAgree() + 1); // 1씩 증가
            return " 매칭완료 버튼을 눌렀습니다.";
        }else{
            matchedList.setMatchingRes("매칭완료");
            classMatchedListRepository.save(matchedList);
            return "매칭인원이 충족 되었습니다.";
        }
    }

    // 노쇼 리스트 반환
    public List<NoShowPublicMatchList> getNoShowPublicMatchList(){
        List<NoShowPublicMatchList> noshow = new ArrayList<>();
        noShowPublicMatchListRepository.findAll().forEach(e->noshow.add(e));
        return noshow;
    }
    public List<NoShowClassMatchList> getNoShowClassMatchList(){
        List<NoShowClassMatchList> noshow = new ArrayList<>();
        noShowClassMatchRepository.findAll().forEach(e->noshow.add(e));
        return noshow;
    }


    //신고 목록 조회
    public List<MatchAccusation> getDeclarationList(String matchingType){
        List<MatchAccusation> list = accusationRepository.findByMatchingType(matchingType);
        return list;
    }
    public MatchAccusation postDeclarationList(MatchAccusation accusation){
        List<String> emailList = new ArrayList<>();
        for (int i = 0 ; i < accusation.getEmail().size() ; i ++) {
            emailList.add(accusation.getEmail().get(i));
        }
        System.out.println(accusation);
        MatchAccusation list = new MatchAccusation();
        list.setEmail(emailList);
        list.setMatchingType(accusation.getMatchingType());
        list.setTitle(accusation.getTitle());
        list.setMatchingId(accusation.getMatchingId());
        list.setComment(accusation.getComment());
        return accusationRepository.save(list);
    }

    public MatchAccusation getPublicDeclarationList(int id, String matchingType){
        MatchAccusation list =accusationRepository.findByMatchingIdAndMatchingType(id, matchingType);
        return  list;
    }

    public MatchAccusation getClassDeclarationList(int id, String matchingType){
        MatchAccusation list =accusationRepository.findByMatchingIdAndMatchingType(id, matchingType);
        return  list;
    }

    public  NoShowPublicMatchList postNoShowPublicUser(NoShowPublicMatchList user){

        NoShowPublicMatchList noShowUser = new NoShowPublicMatchList();
        noShowUser.setEmail(user.getEmail());
        noShowUser.setMatchingId(user.getMatchingId());
        noShowUser.setMatchingType(user.getMatchingType());

        Optional<User> noshowuser = userRepository.findById(user.getEmail());
        noshowuser.get().setRestrctionDate(LocalDateTime.now().plusDays(7)); // 일주일 제한
        userRepository.save(noshowuser.get()); // 다시 저장
        return noShowPublicMatchListRepository.save(noShowUser);
    }

    public  NoShowClassMatchList postNoShowClassUser(NoShowClassMatchList user){
        NoShowClassMatchList noShowUser = new NoShowClassMatchList();
        noShowUser.setMatchingId(user.getMatchingId());
        noShowUser.setEmail(user.getEmail());
        noShowUser.setMatchingType(user.getMatchingType());

        Optional<User> noshowuser = userRepository.findById(user.getEmail());
        noshowuser.get().setRestrctionDate(LocalDateTime.now().plusDays(7)); // 일주일 제한
        userRepository.save(noshowuser.get()); // 다시 저장
        return noShowClassMatchRepository.save(noShowUser);
    }


}
