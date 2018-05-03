<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<c:set var="url" value="${pageContext.request.contextPath}/student/student/list.do?${pageControl.appendParams}"/>
<lesson:page title="user.title.list">
    <jsp:attribute name="css">
        <style type="text/css">
            #name-of-ban-user, #name-of-reset-user {
                font-weight: bold;
                color: red;
            }

            #password-not-match-msg {
                display: none;
                color: #a94442;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script src="static-resource/ace/assets/js/moment.min.js"></script>
		<script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
		<script src="static-resource/ace/assets/js/daterangepicker.min.js"></script>
        <script src="static-resource/ace/assets/js/bootstrap-clockpicker.min.js"></script>
		<script src="static-resource/ace/assets/js/bootstrap-multiselect.min.js"></script>

        <script type="application/javascript">
            $(function() {
                $('#dateRangePicker').daterangepicker({
                    autoUpdateInput:false,
                    "autoApply": false,
                    locale: {
                        format: 'YYYY-MM-DD'
                    }
                },function(start, end) {
                    var startDate = start.format("YYYY-MM-DD");
                    var endDate = end.format("YYYY-MM-DD");
                    $("#startTime").val(startDate);
                    $("#endTime").val(endDate);
                    $("#dateRangePicker").val(startDate + " - " + endDate);
                }).on('cancel.daterangepicker', function(ev, picker) {
                    $("#startTime").val("");
                    $("#endTime").val("");
                    $("#dateRangePicker").val("");
                });
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        <spring:message code="common.homepage"/>
                    </a>
                </li>
                <li class="active">
                    <spring:message code="student.title.list"/>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-user">
            <div class="row">
                <div class="col-xs-12">



                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">

                            <div class="widget-box  <c:if test="${hasOrders}">collapsed</c:if>" >
                                <div class="widget-header ">
                                    <h3 class="widget-title ">
                                        学生信息搜索
                                    </h3>
                                    <div class=" widget-toolbar no-border " style="margin-right: 30px">
                                        <a  data-action="collapse">
                                            <i class="ace-icon fa ${hasOrders ? "fa-chevron-down":"fa-chevron-up"}"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="widget-body">
                                    <div class="row">
                                        <div class="widget-main">
                                            <form id="form" method="get" action="student/student/list.do" class="form-horizontal" role="form">

                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label no-padding-right">学号</label>

                                                    <div class="col-sm-6">
                                                        <input class="form-control " onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                                               value="<c:out value="${vo.number}"></c:out>" minlength="1" maxlength="8" type="text" autocomplete="off" name="number">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label no-padding-right">姓名</label>

                                                    <div class="col-sm-6">
                                                        <input class="form-control " value="<c:out value="${vo.name}"></c:out>" type="text" autocomplete="off" name="name">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label no-padding-right">出生日期</label>
                                                    <div class="col-sm-6">
                                                        <div class="row">
                                                            <div class="col-xs-12 col-sm-12">
                                                                <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-calendar bigger-110"></i>
                                                            </span>
                                                                    <input class="form-control" type="text" autocomplete="off" id="dateRangePicker" <c:if test="${not empty vo.startTime and not empty vo.endTime}">value='<fmt:formatDate pattern="yyyy-MM-dd" value="${vo.startTime}"/> - <fmt:formatDate pattern="yyyy-MM-dd" value="${vo.endTime}"/>'</c:if>>
                                                                    <input type="hidden" name="startTime" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${vo.startTime}"/>" id="startTime">
                                                                    <input type="hidden" name="endTime" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${vo.endTime}"/>" id="endTime">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-12">
                                                    <div class="clearfix ">
                                                        <div class="text-center">
                                                            &nbsp; &nbsp; &nbsp;
                                                            <button class="btn btn-primary btn-sm" type="submit" name="searchButton">
                                                                <spring:message code="button.search"/>
                                                            </button>

                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-12 space-10"></div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="space-2"></div>


                            <h3 class="header smaller lighter blue">
                                <spring:message code="student.title.list"/>
                                <span class=" btn-group pull-right">
                                <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_ADD">
                                    <a href="student/student/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        <spring:message code="button.add"/>
                                    </a>
                                </sec:authorize>
                            	</span>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>id</th>
                                    <th>学号</th>
                                    <th>姓名</th>
                                    <th>性别</th>
                                    <th class="hidden-480">出生日期</th>
                                    <th class="hidden-480">所在班级</th>
                                    <th class="hidden-480">选修科目数</th>
                                    <th class="hidden-480">平均分</th>
                                    <th class="hidden-480">备注</th>
                                    <th class="hidden-480">操作</th>
                                </tr>
                                </thead>

                                <tbody>

                                <c:forEach items="${students.content}" var="student">
                                <tr id="tr-${student.id}">

                                    <td>${student.id}</td>
                                    <td>${student.number}</td>
                                    <td><c:out value="${student.name}"></c:out></td>
                                    <td>
                                        <c:if test="${student.gender == 'M'}">男</c:if>
                                        <c:if test="${student.gender == 'F'}">女</c:if>
                                    </td>
                                    <td>${student.birthday}</td>
                                    <td>${student.grade.name}</td>
                                    <td>${student.subjects.size()}</td>
                                    <td>${student.avgScore}</td>
                                    <td><c:out value="${student.description}"></c:out></td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group action-buttons">

                                            <sec:authorize ifAnyGranted="OPT_STUDENT_SCORE_ENTER">
                                                <a title="录入分数" href="student/student/enter.do?id=${student.id}"
                                                   class="green">
                                                    <i class="ace-icon fa fa-sign-in bigger-120"></i>
                                                </a>
                                            </sec:authorize>

                                            <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_EDIT">
                                                <a title="选课" href="student/student/select.do?id=${student.id}"
                                                   class="green">
                                                    <i class="ace-icon fa fa-tags bigger-120"></i>
                                                </a>
                                            </sec:authorize>

                                            <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_EDIT">
                                                <a title="编辑" href="student/student/edit.do?id=${student.id}"
                                                   class="green">
                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </a>
                                            </sec:authorize>
                                            <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_DELETE">
                                                <c:if test="${param.enabled != 'false'}">
                                                    <a title="删除" class="btn-delete-modal red" data-url="student/student/delete.do"
                                                       data-title="<spring:message code="button.disable"/>"
                                                       data-id="${student.id}">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </a>
                                                </c:if>
                                            </sec:authorize>
                                        </div>
                                    </td>


                                </tr>
                                </c:forEach>
                                </tbody>
                        </table>
                            <lesson:springPagePagination url="${url}" defaultPageSize="5" springPage="${students}"/>

                    </div><!-- /.span -->
                </div><!-- /.row -->

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
        </div>
    </jsp:body>
</lesson:page>
