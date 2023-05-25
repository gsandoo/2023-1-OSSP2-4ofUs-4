<template>
    <!-- 매칭 기록  -->
    <div class="background-setting">
        <div class="container" >
            <div>
                <div class="frame-body">
                    <div>
                        <router-link to="/my" class="my-link">&lt;</router-link>
                        <div style="clear:both;"></div>

                        <div class="matching-img-box">
                            <div class="matching-img"></div>
                        </div>
                        <div class="matching-txt">매칭 결과</div>

                        <div class="matching-refresh" @click="callMatchingRecord()">새로고침</div>
                        <router-link to="/matchingStart" class="matching-btn">매칭추가</router-link>
                        <div style="clear:both;"></div>

                        <div class="line-for-division"></div>

                        <div class="frame-sub-body">
                            <div v-for="(record, index) in matchingListClass" :key="index" class="frame-data-box">
                                <div class="font-hash-h1">
                                    # 신청 날짜 : {{record.matchingTime}}
                                </div>
                                <div class="font-state-box">
                                    <div class="record-img"></div>
                                    <div class="record-type">수업 매칭</div>
                                    <div class="record-head-count">인원 {{record.headCount}}명</div>
                                    <div class="record-timetable">
                                        <div v-for="(timeId, index) in record.courseNumber" :key="index" class="time-id">
                                            {{timeId}}
                                        </div>
                                    </div>
                                    <div v-if="record.matchingRes==='매칭중'" class="record-ing-btn">#매칭 중</div>
                                </div>
                            </div>
                            <div v-for="(record, index) in matchingListFree" :key="index" class="frame-data-box">
                                <div class="font-hash-h1">
                                    # 신청 날짜 : {{record.matchingTime}}
                                </div>
                                <div class="font-state-box">
                                    <div class="record-img"></div>
                                    <div class="record-type">공강 매칭</div>
                                    <div class="record-head-count">인원 {{record.headCount}}명</div>
                                    <div class="record-detail">{{record.availableDay}}</div>
                                    <div class="record-detail">{{record.promiseTime[0].slice(0,5)}}~{{record.promiseTime[1].slice(0,5)}}</div>
                                    <div v-if="record.matchingRes==='매칭중'" class="record-ing-btn">#매칭 중</div>
                                </div>
                            </div>
                            <div class="frame-data-box">
                                <div class="font-hash-h1"># 매칭 기록이 더이상 없습니다.</div>
                                <div class="font-state-box-ex">
                                    매칭을 원하실 경우, 매칭 추가 버튼을 누르십시오.
                                </div>
                            </div>
                            <div :style="{'margin-top': '30px'}"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return {
            matchingListClass: [...this.$store.state.classMatchingRecord].reverse(),
            matchingListFree: [...this.$store.state.publicMatchingRecord].reverse(),
        }
    },
    methods: {
        callMatchingRecord() {
            this.matchingListClass = [...this.$store.state.classMatchingRecord].reverse()
            this.matchingListFree = [...this.$store.state.publicMatchingRecord].reverse()
            console.log(this.$store.state.publicMatchingRecord)
            console.log(this.$store.state.classMatchingRecord)
        },

    },
}
</script>


<style lang="scss" scoped>
    @import "../../scss/main";
    
    // 배경화면 설정
    .background-setting{
        height: 100vh;
        width: 100vw;
        margin:0;

        background-color: #ECBC76; 
        display: grid;
        grid-template-rows: auto;
        justify-items: center;
        align-items: center;
    }
    // container 클래스 위치 조정
    .container{
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .frame-body{
        width: 996px;
        height: 569px;
        background-color: #FFFEF9;
        border-radius: 20px;
        
        .my-link{
            width: 51px;
            height: 46px;

            margin-top: 0px;
            margin-left: 17px;
            float:left;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 400;
            font-size: 50px;
            line-height: 75px;
            color: #B87514;
            display: flex;
            align-items: center;
        }
        .matching-img-box{
            width: 68px;
            height: 55px;
            
            margin-top: 0px;
            margin-left: 68px;
            float: left;

            display: flex;
            justify-content: left;
            align-items: center;
        }
        .matching-img{
            width: 40px;
            height: 40px;
            
            float: left;

            background-image: url("../../assets/mypage/matching.png");
            background-size: cover;
            background-repeat: no-repeat;
        }
        .matching-txt{
            width: 200px;
            height: 55px;

            margin-top: 0px;
            margin-left: 0px;
            float: left;

            display: flex;
            justify-content: left;
            align-items: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 400;
            font-size: 30px;
            line-height: 45px;
        }
        .matching-refresh{
            width: 163px;
            height: 55px;
            background-color: #B87514;

            margin-top: 0px;
            margin-left: 261px;
            border-radius: 20px;
            float: left;

            color: #FFFFFF;
            display: flex;
            justify-content: center;
            align-items: center;

            font-family: 'Inter';
            font-style: normal;
            font-weight: 400;
            font-size: 23px;
            line-height: 28px;
        }
        .matching-btn{
            width: 163px;
            height: 55px;
            background-color: #B87514;

            margin-top: 0px;
            margin-left: 22px;
            border-radius: 20px;
            float: left;

            color: #FFFFFF;
            display: flex;
            justify-content: center;
            align-items: center;

            font-family: 'Inter';
            font-style: normal;
            font-weight: 400;
            font-size: 23px;
            line-height: 28px;
        }

        .line-for-division{
            width: 891px;
            height: 1px;
            margin-top: 30px;
            margin-left: 53px;
            margin-bottom: 0px;

            border: 1px solid #B87514
        }
    }
    .frame-sub-body{
        width: 996px;
        height: 432px;
        margin-top: 5px;
        margin-left: 0px;

        background: #FFFEF9;
        border-radius: 20px;
        overflow-y: scroll;
        .frame-data-box{
            width: 996px;
            height: 160px;

            margin-top: 30px;
            margin-left: 53px;
        }
        .font-hash-h1{
            width: 891px;
            height: 34px;

            display: flex;
            justify-content: left;
            align-items: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 500;
            font-size: 25px;
            line-height: 38px;
            color: #B87514;
        }
        .font-state-box{
            width: 891px;
            height: 110px;

            margin-top: 16px;

            border: 5px solid #ECBC76;
            border-radius: 20px;
            .record-img{
                width: 50px;
                height: 51px;
                
                margin-top: 26px;
                margin-left: 27px;
                float: left;

                background-image: url("../../assets/mypage/matching/people.png");
                background-size: cover;
                background-repeat: no-repeat;
            }
            .record-type{
                width: 100px;
                height: 74px;

                margin-top: 18px;
                margin-left: 49px;
                float: left;

                font-family: 'Poppins';
                font-style: normal;
                font-weight: 500;
                font-size: 25px;
                line-height: 38px;

                display: flex;
                align-items: center;
                justify-content: left;
                color: #B87514;
            }
            .record-head-count{
                width: 100px;
                height: 74px;

                margin-top: 18px;
                float: left;

                font-family: 'Poppins';
                font-style: normal;
                font-weight: 300;
                font-size: 25px;
                line-height: 45px;
                display: flex;
                align-items: center;
                justify-content: center;
                text-align: center;

                color: #B87514;
            }
            .record-detail{
                width: 170px;
                height: 74px;

                margin-top: 18px;
                float: left;

                font-family: 'Poppins';
                font-style: normal;
                font-weight: 300;
                font-size: 25px;
                line-height: 45px;
                display: flex;
                align-items: center;
                justify-content: center;
                text-align: center;

                color: #B87514;
            }

            .record-timetable{
                width: 340px;
                height: 74px;

                margin-top: 18px;
                float: left;
                overflow-x: auto;

                font-family: 'Poppins';
                font-style: normal;
                font-weight: 300;
                font-size: 25px;
                line-height: 45px;
                display: flex;
                align-items: center;
                justify-content: center;
                text-align: center;

                color: #B87514;
                .time-id{
                    width: 120px;
                    height: 50px;

                    float: left;

                    display: flex;
                    align-items: center;
                    justify-content: center;
                    text-align: center;
                    
                    font-family: 'Poppins';
                    font-style: normal;
                    font-weight: 300;
                    font-size: 20px;
                    line-height: 45px;
                    color: #FFFFFF;

                    background: #B87514;
                    border-radius: 10px;
                }
            }
            .record-ing-btn{
                width: 150px;
                height: 72px;

                margin-top: 18px;
                margin-left: 35px;
                float: left;

                border: 5px solid #ECBC76;
                border-radius: 20px;

                align-items: center;
                text-align: center;
                justify-content: center;

                font-family: 'Poppins';
                font-style: normal;
                font-weight: 300;
                font-size: 30px;
                line-height: 45px;
                display: flex;

                color: #B87514;
            }
        } 
        .font-state-box-ex{
            width: 891px;
            height: 110px;

            margin-top: 16px;

            border: 5px solid #ECBC76;
            border-radius: 20px;

            display: flex;
            justify-content: center;
            align-items: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 500;
            font-size: 25px;
            line-height: 38px;
            color: #B87514;
        }  
    }
</style>