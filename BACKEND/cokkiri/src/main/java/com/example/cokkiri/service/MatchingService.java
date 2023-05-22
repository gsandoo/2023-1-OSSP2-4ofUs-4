package com.example.cokkiri.service;

import com.example.cokkiri.model.*;
import com.example.cokkiri.repository.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDate;
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
    private MatchedListRepository classMatchedListRepository;

    // 공강 레포지토리
    @Autowired
    private PublicMatchedListRepository publicMatchedListRepository;
    @Autowired
    private MatchingAgreeRepository matchingAgreeRepository;
    @Autowired
    private NoShowPublicMatchListRepository noShowPublicMatchRepository;
    @Autowired
    private NoShowClassMatchListRepository noShowClassMatchRepository;
    @Autowired
    private AccusationRepository accusationRepository;



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




    public PublicMatchedList findPublicMatch(List<PublicMatching>userList , int count ) {
        if (userList.size() < 2) {
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

                    // 객체 생성
                    PublicMatchedList matched = new PublicMatchedList();

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
        return null;
    };

    //수업매칭
    public ClassMatchedList findClassMatch(List<ClassMatching>userList , int count ){

        if(userList.size()<2){
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
                    // 객체 생성
                    ClassMatchedList matched = new ClassMatchedList();
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
            return null;
        }
    }

    @Autowired
    NotificationService notificationService;
    public PublicMatchedList publicMatch(PublicMatching user){
        // 매칭된 사람 수 = 희망인원
        int count = user.getHeadCount();
        publicLectureUsers.add(user);
        PublicMatchedList publicMatchedList = new PublicMatchedList();
        publicMatchedList = findPublicMatch(publicLectureUsers, count);
        if (publicMatchedList != null) {
            return savePublicUser(publicMatchedList);
        }
        return null;
    }



    public ClassMatchedList classMatch(ClassMatching user){
        // 매칭된 사람 수 = 희망인원
        int count = user.getHeadCount();
        classLectureUsers.add(user);
        ClassMatchedList classMatchedList = new ClassMatchedList();
        classMatchedList = findClassMatch(classLectureUsers,count);
        if(classMatchedList !=null){
            return saveClassUser(classMatchedList);
        }
        return null;
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
        List<PublicMatchedList> matchedlist = new ArrayList<>();
        publicMatchedListRepository.findAll().forEach(e->matchedlist.add(e));
        return matchedlist;
    }

    //공강 매칭 Id로 찾아서 반환
    public List<PublicMatchedList> findPublicMatchingById(String id){
        return publicMatchedListRepository.findByEmailListContains(id);
    }


    public ClassMatchedList saveClassUser(ClassMatchedList matchedList){
        return classMatchedListRepository.save(matchedList); // 데베에 저장

    };
    public PublicMatchedList savePublicUser(PublicMatchedList matchedList){
        return publicMatchedListRepository.save(matchedList); // 데베에 저장

        // 노쇼용
//        int id = matchedList.getMatchingId();
//        String type = matchedList.getMatchingType();
//        LocalTime hour = matchedList.getPromiseTime().get(0).plusHours(1);

    };

    public  PublicMatchedList deletePublicUser(PublicMatchedList matchedList){
        publicMatchedListRepository.delete(matchedList);
        return matchedList;
    }

    public ClassMatchedList deleteClassUser(ClassMatchedList matchedList){
        classMatchedListRepository.delete(matchedList);
        return matchedList;
    }


    List<MatchingAgree> matchingAgreeList = new ArrayList<>();
    public MatchingAgree publicMatchAgree(String id) {
        MatchingAgree matchingAgree = new MatchingAgree();
        List<PublicMatchedList> list = publicMatchedListRepository.findByEmailListContains(id);
        matchingAgree.setMatchingId(list.get(0).getMatchingId());
        matchingAgree.setMatchingType(list.get(0).getMatchingType());
        List<String> emailList = matchingAgree.getEmail();
        emailList.add(id);
        matchingAgree.setEmail(emailList);
        list.get(0).setMatchingAgree(list.get(0).getMatchingAgree()+1);
        if(list.get(0).getMatchingAgree()==list.get(0).getHeadCount()){
            System.out.println("매칭인원 충족되었습니다.");
            list.get(0).setMatchingRes("매칭 완료");
        }
        list.get(0).setMatchingId(list.get(0).getMatchingId());
        publicMatchedListRepository.save(list.get(0));
        return matchingAgreeRepository.save(matchingAgree);
    }

    public MatchingAgree classMatchAgree(String id) {
        MatchingAgree matchingAgree = new MatchingAgree();
        List<ClassMatchedList> list = classMatchedListRepository.findByEmailListContains(id);
        matchingAgree.setMatchingId(list.get(0).getMatchingId());
        matchingAgree.setMatchingType(list.get(0).getMatchingType());
        List<String> emailList = matchingAgree.getEmail();
        emailList.add(id);
        matchingAgree.setEmail(emailList);
        list.get(0).setMatchingAgree(list.get(0).getMatchingAgree()+1);
        if(list.get(0).getMatchingAgree()==list.get(0).getHeadCount()){
            System.out.println("매칭인원 충족되었습니다.");
            list.get(0).setMatchingRes("매칭 완료");
        }
        list.get(0).setMatchingId(list.get(0).getMatchingId());
        classMatchedListRepository.save(list.get(0));
        return matchingAgreeRepository.save(matchingAgree);
    }

    // 노쇼 리스트 반환
    public List<NoShowPublicMatchList> getNoShowPublicMatchList(){
        List<NoShowPublicMatchList> noshow = new ArrayList<>();
        noShowPublicMatchRepository.findAll().forEach(e->noshow.add(e));
        return noshow;
    }
    public List<NoShowClassMatchList> getNoShowClassMatchList(){
        List<NoShowClassMatchList> noshow = new ArrayList<>();
        noShowClassMatchRepository.findAll().forEach(e->noshow.add(e));
        return noshow;
    }


    // 노쇼 procedure.
    // MatchingAgree 에서 email과 매칭타입으로 찾고, 마찬가지로 매칭리스트에서도 찾는다.

    // email , matchingType -> 매치된 유저의 이메일과 메칭타입 , hour -> 매치된 리스트에서 시작시간+한시간 지난 시간.
//    public void chekNoshow(String email,String matchingType, LocalTime hour){
//        List<MatchingAgree> agreeUserList = matchingAgreeRepository.findByEmailAndMatchingType(email,matchingType);
//        List<PublicMatchedList> matchedList = publicMatchedListRepository.findByEmailListContains(email);
//
//    }




    // 두 entitiy 내 이메일리스트내의 원소를 비교, 없는 원소(이메일)를 찾는다.
//    @Scheduled(cron = "${cronExpression}")
//    public String getNoShowUserEmailList(String one[], String two[]) {
//        String data="";
//        int check = 0;
//        try {
//            if(one.length > 0 && two.length > 0) { //두 배열 데이터가 널이 아닐 경우
//                for(int i=0; i<one.length; i++) {
//                    for(int j=0; j<two.length; j++) {
//                        if(one[i].equals(two[j]) == true) { //배열 값이 같은게 있을 경우
//                            check ++; //체크값 증가 실시
//                        }
//                    }
//                    if(check <= 0) { //같은 값이 존재하지 않을 경우
//                        if(data.contains(one[i]) == false) { //중복해서 데이터를 저장하지 않기 위함 (포함하지 않을 경우)
//                            data += String.valueOf(one[i] + " ");
//                        }
//                    }
//                    check = 0; //값 초기화 실시
//                }
//            }
//            else {
//                System.out.println("두 배열 데이터가 포함된지 확인해 주세요 ... ");
//            }
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//        return data;
//    }//클래스 종료



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

    public MatchAccusation getPublicDeclarationList(String id, String matchingType){
        MatchAccusation list =accusationRepository.findByMatchingIdAndMatchingType(id, matchingType);
        return  list;
    }

    public MatchAccusation getClassDeclarationList(String id, String matchingType){
        MatchAccusation list =accusationRepository.findByMatchingIdAndMatchingType(id, matchingType);
        return  list;
    }

    public  NoShowPublicMatchList postNoShowPublicUser(NoShowPublicMatchList user){
            List<String> emailList = new ArrayList<>();
            for(int i = 0 ; i < user.getEmail().size() ; i++){
                emailList.add(user.getEmail().get(i));
            }
            NoShowPublicMatchList noShowUser =new NoShowPublicMatchList();
            noShowUser.setEmail(emailList);
            noShowUser.setMatchingId(user.getMatchingId());
            noShowUser.setMatchingType(user.getMatchingType());
            System.out.println(noShowUser);
            return noShowPublicMatchRepository.save(noShowUser);
    }

    public  NoShowClassMatchList postNoShowClassUser(NoShowClassMatchList user){
        NoShowClassMatchList noShowUser = new NoShowClassMatchList();
        List<String> emailList = new ArrayList<>();
        for(int i = 0 ; i < user.getEmail().size() ; i++){
            emailList.add(user.getEmail().get(i));
        }
        noShowUser.setEmail(emailList);
        noShowUser.setMatchingId(user.getMatchingId());
        noShowUser.setMatchingType(user.getMatchingType());
        return noShowClassMatchRepository.save(noShowUser);
    }
}
