<template>
    <section class="main">
       
            <Table stripe highlight-row  
                   :columns="tableColumns1" 
                   :data="tableData" 
                    border
                   size="small" 
                   ref="table"     
                   @on-expand="onExpand"  
                   @on-select="onSelect"
                   @on-selection-change='selectionClick'
                  >
            </Table>        
         
       
    </section>
</template>
 
<script> 
    import expandRow from './table-expand.vue';
    export default {   
        components: { expandRow }, 
        data () {
            return {               
                tableData:[],                 
                //行数据标题
                tableColumns1:[
                    {
                        type: 'expand',
                        title:"#",
                        width: 50,
                        render: (h, params) => {
                            return h(expandRow, {
                                props: {  row: params.row  },
                                // on:    {  click: () => { this.expand(params.row, params.index)} }
                                
                            })
                        }
                    },
                    {
                        title: '线路名称',
                        key: 'lineName'
                    },
                    {
                        title: '线路方向',
                        key: 'lineDirection'
                    },
                    {
                        title: '始发站',
                        key: 'startStation'
                    },
                    {
                        title: '经停站',
                        key: '_text',    
                        width:120,
                        align: 'center',
                        render: (h, params) => {   
                            return h("span", {
                                props: {  row: params.row  },
                                style: {  color: 'tomato'} ,         
                                on:    {  click: () => { this.expand(params.row, params.index)} }                                
                            },params.row._text)
                        }
                    },
                    {
                        title: '终点站',
                        key: 'endStation'
                    },
                    {
                        title: '线路类型',
                        key: 'lineType'
                    },
                    {
                        title: '运营里程',
                        key: 'mileage'
                    },
                    {
                        title: '线路运营单位',
                        key: 'operateDepart'
                    },
                    {
                        title: '票价',
                        key: 'price'
                    },
                ],
               
                // 数据源
                tableData1: [
                    {   
                       id:12,
                       lineName:"公主坟线",
                       lineDirection:"机场-市区",
                       startStation:"T1",
                       stopStation:"all",
                       endStation:"公主坟",
                       lineType:"市区",
                       mileage:"100Km",
                       operateDepart:"PEK运营一部",
                       price:"20元",
                       stopStation:[
                            {stopStation:"东直门",id:11},
                            {stopStation:"雍和宫",id:22}, 
                            {stopStation:"安定门",id:33},
                            {stopStation:"西直门",id:33},
                            {stopStation:"复兴门",id:33},
                            {stopStation:"建国门",id:33}
                       ]
                                                            
                    },
                    {  
                       id:13, 
                       lineName:"公主坟线",
                       lineDirection:"机场-市区",
                       startStation:"T1",
                       stopStation:"all",
                       endStation:"公主坟",
                       lineType:"市区",
                       mileage:"100Km",
                       operateDepart:"PEK运营一部",
                       price:"20元" ,
                       stopStation:[
                            {stopStation:"东直门",id:11},
                            {stopStation:"西直门",id:22}, 
                            {stopStation:"建国门",id:33}
                       ]                            
                    },
                     {  
                       id:14, 
                       lineName:"公主坟线",
                       lineDirection:"机场-市区",
                       startStation:"T1",
                       stopStation:"all",
                       endStation:"公主坟",
                       lineType:"市区",
                       mileage:"100Km",
                       operateDepart:"PEK运营一部",
                       price:"20元" ,
                       stopStation:[
                            {stopStation:"东直门",id:11},
                            {stopStation:"西直门",id:22}, 
                            {stopStation:"建国门",id:33}
                       ]                         
                    },
                     {  
                       id:15, 
                       lineName:"公主坟线",
                       lineDirection:"机场-市区",
                       startStation:"T1",
                       stopStation:"all",
                       endStation:"公主坟",
                       lineType:"市区",
                       mileage:"100Km",
                       operateDepart:"PEK运营一部",
                       price:"20元" ,
                       stopStation:[
                            {stopStation:"东直门",id:11},
                            {stopStation:"西直门",id:22}, 
                            {stopStation:"建国门",id:33}
                       ]                      
                    },
                    {  
                       id:16, 
                       lineName:"公主坟线",
                       lineDirection:"机场-市区",
                       startStation:"T1",
                       stopStation:"all",
                       endStation:"公主坟",
                       lineType:"市区",
                       mileage:"100Km",
                       operateDepart:"PEK运营一部",
                       price:"20元" ,
                       stopStation:[
                            {stopStation:"东直门",id:11},
                            {stopStation:"西直门",id:22}, 
                            {stopStation:"建国门",id:33}
                       ]
                           
                    }                       
                    
                ]
               
               
            }
        },  
        created(){
          
        }, 
        watch:{
 
        }, 
        mounted () {          
            this.tableData = this.tableData1.map(item => {
                item._expanded = false
                item._text = "查看所有经停站"
                return item
            })
            console.log(this.tableData);
            
 
        },  
        methods: {
            
            //点击展开收起
            expand(item,index){
                // console.log(item,index); 
                // 展开点击项,展开之前恢复到初始状态
                this.tableData.sort() //更新视图
                for (let i = 0; i <  this.tableData.length; i++) {
                    this.tableData[i]._expanded = false;   
                    this.tableData[i]._text = "查看所有经停站"                       
                } 
                //点击展开
                if (item._expanded) {  
                    // this.tableData.splice()  
                    // this.tableData.push()  
                    this.tableData.sort() //更新视图 
                    this.tableData[index]._expanded = false
                    this.tableData[index]._text = "查看所有经停站"                        
                }else{
                    this.tableData.splice()  
                    this.tableData[index]._expanded = true
                    this.tableData[index]._text = "收起所有经停站" 
                }
                     
            },
 
            // 展开原生事件  点击左侧展收起           
            onExpand(row, status){
                // console.log(row,status);  
                // console.log(this.tableData);
                if(status){
                    this.tableData1.splice()                 
                    this.tableData1.filter((item, index)=>{  
                        if(item.id == row.id){   
                            item._expanded = true;   //展开选中的行
                            // this.$set(item, "_text", "收起所有经停站")                           
                            item._text = "收起所有经停站"   
                        }else{
                            item._expanded = false;   //其他行关闭 
                            item._text = "查看所有经停站"                          
                        }
                        return item; 
                    });                  
                    this.tableData = this.tableData1                
                }else{                
                    this.tableData.splice()   
                    this.tableData.map((item, index)=>{                                                    
                        if(item.id == row.id){   
                            item._expanded = false;   //展开选中的行
                            // this.$set(item, "_text", "查看所有经停站")                           
                            item._text = "查看所有经停站"   
                        }else{
                            item._expanded = false;   //其他行关闭    
                            item._text = "查看所有经停站"                       
                        }                      
                        return item; 
                    });
                       
                }
            },
          
        }
    }
</script>
<style scoped>
   
</style>