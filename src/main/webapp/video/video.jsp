<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    //延迟加载
    $(function(){
        pageInit();
    });



    //创建表格
    function pageInit(){
        $("#userTable").jqGrid({
            url : "${path}/video/queryVideoPage",  //接收  page=当前页   rows=每页展示条数   返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
            editurl:"${path}/video/edit",  //增删改走的路径  oper:add ,edit,del
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
            colNames:['编号','标题','简介','视频','封面','上传时间','类别编号','分组编号','用户编号'],
            colModel : [
                {name:'id',align:'center'},
                {name:'title',align:'center',editable:true},
                {name:'description',align:'center',editable:true},
                {name:'videoPath',editable:true,align:'center',edittype:'file',
                    formatter:function (value,options,row) {
                        return '<video src="'+value+'" controls="controls" style="width: 100%; height: 90px; poster:"'+row.coverPath+'" >'
                    }
                },
                {name:'coverPath',align:'center',
                    formatter:function (value,options,row) {
                        return '<img src="'+value+'" style="width: 100px;height: 100px" class="img-circle" />'
                    }
                },
                {name:'uploadTime',align:'center'},
                {name:'categoryId',align:'center',edittype:'select',editoptions:{dataUrl:'${path}/category/findAll'}},
                {name:'groupId',align:'center'},
                {name:'userId',align:'center'}
            ]
        });
        //分页工具栏
        //处理曾删改查操作   工具栏
        $("#userTable").jqGrid('navGrid', '#userPage',
            {edit: true, add: true, del: true, edittext: "修改", addtext: "添加", deltext: "删除"},
            {
                closeAfterEdit: true,  //关闭对话框
                beforeShowForm: function (obj) {
                    obj.find("#videoPath").attr("disabled", true);//禁用input
                }
            }, //执行修改之后的额外操作
            {
                closeAfterAdd: true, //关闭添加的对话框
                afterSubmit: function (data) {
                    $.ajaxFileUpload({
                        fileElementId: "videoPath",    //需要上传的文件域的ID，即<input type="file">的ID。
                        url: "${path}/video/uploadUserCover", //后台方法的路径
                        type: 'post',   //当要提交自定义参数时，这个参数要设置成post
                        data: {id: data.responseText},  //responseText: "74141b4c-f337-4ab2-ada2-c1b07364adee"
                        success: function () {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                            //刷新页面
                            $("#userTable").trigger("reloadGrid");
                        }
                    });
                    //必须要有返回值
                    return "hello";
                }
            }, //执行添加之后的额外操作
            {
                closeAfterDel: true, //关闭添加的对话框
            } //执行删除之后的额外操作
        );
    };


</script>

<%--创建一个面板--%>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <span>视频信息</span>
    </div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户视频</a></li>
    </ul><br>



    <%--创建表格--%>
    <table id="userTable" />

    <%--分页工具栏--%>
    <div id="userPage"/>

</div>