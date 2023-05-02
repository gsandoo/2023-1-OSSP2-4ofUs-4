<template>
    <!-- 이곳에 회원가입 뼈대 생성. 추후 컴포넌트로 빼내서 활용할 예정.  -->
    <div class="container" >
        <div class="form-frame">
            <div :style="{'margin-left': '341px'}">
                <p :style="{'margin-left': '9px','margin-top':'53px','margin-bottom':'0','font-size': '16px','color': '#8D8D8D'}">
                    계정이 있으신가요 ?
                </p>
                <router-link to="/" :style="{'margin-left': '95px','color': '#B87514'}">로그인</router-link>
            </div>
            <h2 :style="{'padding-top': '10px','margin-left': '44px'}">회원가입</h2>
            <p :style="{'margin-top': '52px','margin-left': '44px','margin-bottom': '0px'}"><span :style="{'font-size':'22px',color:'red'}">*</span>동국대 웹메일 주소를 입력하세요</p>
            <input
                name="emailAddress" 
                placeholder="email address"
                :style="bigInputStyle" 
                @keydown.enter="returnEmailValidationResults"
                @change="returnEmailValidationResults"
                v-model="member.userEmailAddress">
            <p 
                v-if="formControl.isGoodEmail"
                :style="{'margin-left':'44px','color':'royalblue'}">
                동국대 웹메일 주소가 확인 되었습니다.</p>
            <p 
                v-if="formControl.isErrorEmail"
                :style="{'margin-left':'44px','color':'red'}">
                동국대 웹메일만 가능합니다. 잘못된 입력입니다.</p>
            <p :style="{'margin-top': '35px','margin-left': '44px','margin-bottom': '0px'}"><span :style="{'font-size':'22px',color:'red'}">*</span>비밀번호</p>
            <input 
                type="password"
                name="password" 
                placeholder="password" 
                :style="bigInputStyle"
                @keydown.enter="returnPasswordAgainValidationResult"
                @change="returnPasswordAgainValidationResult"
                v-model="member.userPassword" >
            <p :style="queryStyle"><span :style="{'font-size':'22px',color:'red'}">*</span>재확인</p>
            <input
                type="password"
                placeholder="password again"
                :style="bigInputStyle"
                @keydown.enter="returnPasswordAgainValidationResult"
                @change="returnPasswordAgainValidationResult(), turnOnPasswordAgainValidation()"
                v-model="member.userPasswordAgain">
            <div v-if="formControl.onPasswordAgainValidation">
                <p 
                v-if="formControl.isGoodPasswordAgain"
                :style="{'margin-left':'44px','color':'royalblue'}">
                확인되었습니다.</p>
                <p 
                v-if="formControl.isErrorPasswordAgain"
                :style="{'margin-left':'44px','color':'red'}">
                비밀번호가 일치하지 않습니다.</p>
            </div>
            <p :style="[queryStyle,{'margin-top':'35px'}]"><span :style="{'font-size':'22px',color:'red'}">*</span>학번</p>
            <input
                placeholder="student ID"
                :style="[bigInputStyle,{'margin-bottom':'17px'}]" 
                v-model="member.userStudentId">
            <p :style="queryStyle">이름</p>
            <input
                placeholder="name"
                :style="bigInputStyle" 
                v-model="member.userName">
            <p :style="queryStyle">닉네임</p>
            <input
                placeholder="nickname"
                :style="bigInputStyle" 
                v-model="member.userNickname">
            <p :style="queryStyle">학과</p>
            <input
                placeholder="소속"
                :style="bigInputStyle" 
                v-model="member.userDepartment">
            <p :style="queryStyle">성별</p>
            <input
                type="radio"
                id="male"
                :style="{'margin-top': '13px','margin-left': '44px'}"
                value="male" v-model="member.userSex">
                <label for="male">남자</label>
            <input
                type="radio"
                id="female"
                :style="{'margin-left': '10px'}" 
                value="female" v-model="member.userSex">
                <label for="female">여자</label>
            <p :style="queryStyle">나이</p>
            <input 
                placeholder="age"
                :style="bigInputStyle" 
                v-model="member.userAge">
            <button 
                @click= "submitForm"
                :style="ButtonStyle">
                확인
            </button>
        </div>
    </div>
</template>

<script>
export default {
    // back-end의 API와 연결 필요함
    data() {
        return {
            // 회원가입 유저의 입력 폼
            member: {
                userEmailAddress: '',
                userPassword: '',
                userPasswordAgain: '',
                userStudentId: '',
                userName: '',
                userNickname: '',
                userDepartment: '',
                userSex: '',
                userAge: ''
            },
            // 유효성 상태 표시
            formControl:    {
                // email 유효성
                isGoodEmail: false,
                isErrorEmail: false,
                // passwordAgain
                isGoodPasswordAgain: false,
                isErrorPasswordAgain: false,
                onPasswordAgainValidation: false
            },
            // 스타일 객체
            bigInputStyle: {
                'margin-top': '13px',
                width: '451px',
                height: '50px',
                'margin-left': '44px',
                background: '#FFFFFF',
                border: '1px solid #4285F4',
                'border-radius': '9px'
            },
            ButtonStyle: {
                'box-shadow': '0px 4px 19px rgba(119, 147, 65, 0.3)',
                'border-radius': '10px',
                background: '#E48700',
                'margin-top': '32px',width: '451px', height: '54px',
                'margin-left': '44px', 'margin-right': '44px',
                'margin-bottom': '78px',
                'border-color': '#E48700',
                color: '#FFFFFF'
            },
            queryStyle: {
                'margin-top': '13px',
                'margin-left': '44px',
                'margin-bottom': '0px'
            }
    }},
    methods: {
        // 이메일 유효성 판단
        returnEmailValidationResults(){
            if(this.member.userEmailAddress.endsWith('@dongguck.edu') | this.member.userEmailAddress.endsWith('@dgu.ac.kr')){
                this.formControl.isGoodEmail = true
                this.formControl.isErrorEmail = false
            }
            else{
                this.formControl.isGoodEmail = false
                this.formControl.isErrorEmail = true
            }
        },
        // password again 유효성 판단
        returnPasswordAgainValidationResult(){
            if(this.member.userPassword === this.member.userPasswordAgain){
                this.formControl.isGoodPasswordAgain = true
                this.formControl.isErrorPasswordAgain = false
            }
            else{
                this.formControl.isGoodPasswordAgain = false
                this.formControl.isErrorPasswordAgain = true
            }
        },
        turnOnPasswordAgainValidation(){
            this.formControl.onPasswordAgainValidation = true
        },
        // 작성한 폼 제출하는 부분. back-end의 API와 연결 필요함
        submitForm(){
            if(this.formControl.isGoodEmail & !(this.member.userPassword==='') & this.formControl.isGoodPasswordAgain & !(this.member.userStudentId=='')){
                alert(this.member.userEmailAddress+"(으)로 인증 메일을 보냈습니다.")
                this.$router.replace('/')
            }
            else{
                alert("잘못된 입력입니다.")
            }
        }
    },
    watch: {

    }
}
</script>


<style lang="scss" scoped>
    @import "../scss/main";
    // container 클래스 위치 조정
    .container{
        display: flex;
        align-items: center;
        justify-content: center;
    }
    // form 틀 만들기
    .form-frame {
        width: 539px;
        height: 741px;
        background: #FFFFFF;
        box-shadow: 0px 4px 35px rgba(0, 0, 0, 0.08);
        border-radius: 40px;
        overflow-y: scroll;
    }
</style>