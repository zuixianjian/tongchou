// table-expand.vue
<style scoped>
    .expand-row{
        margin-bottom: 16px;
    }
</style>
<template>
    <div>     
        <Row>
            <Col>
                <Steps :current="10">
                    <Step 
                        v-for="item in stopStationList" 
                        :title="item.stopStation" 
                        icon="ios-subway" 
                        :content="item._index"
                        :value="item.id" 
                        :key="item.id"
                    ></Step> 
                </Steps>
            </Col>
        </Row>
      
    </div>
</template>
<script>
    export default {
        props: {
            row: Object
        },
             
        data () {
            return {
                valueRow:this.row.stopStation,
                stopStationList:[]
            }
        },
        //  stopStation:[
        //     {stopStation:"东直门",id:11},
        //     {stopStation:"西直门",id:22}, 
        //     {stopStation:"建国门",id:33}
        // ]    
        created(){
        //    console.log(this.valueRow);
        //    this.stopStationList = this.row.stopStation
        //   给数据添加index
           this.stopStationList = this.row.stopStation.map((item,index) => {
                item._index = "经停站"+(index+1)              
                return item
            })
          
        }, 
        methods: {
 
        }
    };
</script>