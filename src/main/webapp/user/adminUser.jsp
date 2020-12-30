<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    //延迟加载
    $(function(){
        pageInit();
    });

    function updateStatus(key) {
        alert(key)
        $.ajax({
            url: "${pageContext.request.contextPath}/Admin/updateStatus",
            type: "post",
            data: 'id='+key,
            success : function () {
                $("#userTable").trigger("reloadGrid");
            }
        })
    }

    //创建表格
    function pageInit(){
        $("#userTable").jqGrid({
            url : "${path}/Admin/queryUserPage",  //接收  page=当前页   rows=每页展示条数   返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
            editurl:"${path}/Admin/edit",  //增删改走的路径  oper:add ,edit,del
            datatype : "json", //数据格式
            rowNum : 2,  //每页展示条数
            rowList : [ 2,10, 20, 30 ],  //可选没夜战是条数
            pager : '#userPage',  //分页工具栏
            sortname : 'id', //排序
            type : "post",  //请求方式
            styleUI:"Bootstrap", //使用Bootstrap
            autowidth:true,  //宽度自动
            height:"auto",   //高度自动
            viewrecords : true, //是否展示总条数
            colNames : [ 'Id', '用户名', '密码', '状态', "salt" ],
            colModel : [
                {name : 'id',width : 55},
                {name : 'username',editable:true,width : 100},
                {name : 'password',editable:true,width : 100},
                {name : 'status',width : 80,align : "center",
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            //正常  展示冻结（绿色）
                            return "<a class='btn btn-success' onclick='updateStatus(\""+rowObject.id+"\")'>冻结</a>";
                        }else{
                            //冻结  展示解除冻结（红色）
                            return "<a class='btn btn-danger' onclick='updateStatus(\""+rowObject.id+"\")' >解除冻结</a>";
                        }
                    }
                },
                {name : 'salt',editable:true,width : 100},
            ]
        });

        //分页工具栏
        $("#userTable").jqGrid('navGrid', '#userPage',
            {edit : true,add : true,del : true,addtext:"添加",edittext:"编辑",deltext:"删除"},
            {
                closeAfterEdit:true,  //关闭对话框

            },  //修改之后的额外操作
            {


            }, //添加之后的额外操作

            {}  //删除之后的额外操作

        );
    }

</script>

<%--创建一个面板--%>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <span>用户信息</span>
    </div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户信息</a></li>
    </ul><br>

    <div>
        <button class="btn btn-info">导出用户信息</button>
        <button class="btn btn-info">测试</button>
    </div><br>

    <%--创建表格--%>
    <table id="userTable" />

    <%--分页工具栏--%>
    <div id="userPage"/>

</div>