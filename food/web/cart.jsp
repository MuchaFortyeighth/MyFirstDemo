<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: xxxx
  Date: 2018/8/29
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="utf-8"%>

<HTML>
<HEAD>
    <base href="http://localhost:8080/food/">
    <TITLE>我学我会网上订餐系统</TITLE>
    <META http-equiv=Content-Type content="text/html; charset=utf-8">
    <link rel="stylesheet" href="./css/styles.css" type="text/css" />

    <script language="javascript" type="text/javascript" src="js/javaScript.js"></script>
    <style>
        .imageBlock{
            width: 150px;
            border: 1px solid #333333;
            float: left;
        }
        .detailBlock{
            float: left;
        }
        .detailBlock ul li{
            font-size: small;
        }
        .clear{clear:both;}
    </style>
</HEAD>
<BODY leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="BodyTop.jsp" />
<TABLE cellSpacing=0 cellPadding=0 width=776 align=center border=0>
    <TBODY>
    <TR vAlign=top>
        <TD width=181 background="images/002.gif"><jsp:include
                page="BodyLeft.jsp" /></TD>
        <TD><jsp:include page="BodyInnerTop.jsp" />
            <TABLE cellSpacing=0 cellPadding=0 width="96%" align=center border=0>
                <TBODY>
                <TR>
                    <TD>

                        <TABLE cellSpacing=1 cellPadding=1 width="100%" align=center
                               bgColor=#c0c0c0 border=0>
                        </TABLE>
                        <BR>
                    </TD>
                </TR>
                </TBODY>
            </TABLE>

            <TABLE style="TEXT-ALIGN: center; border-collapse: collapse;" cellSpacing=0 cellPadding=0 width=590 border=1>
                <!--   显示内容开始  -->
                <tr>
                    <th>餐品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                </tr>
                <c:set var="total" value="${0}" scope="session"/>
                <c:forEach var="item" items="${cart.values()}">
                    <c:set var="total" value="${total+item.subtotal}" scope="session"/>
                    <tr>
                        <td><a href="books/view.do?foodId=${item.food.foodId}">${item.food.foodName}</a></td>
                        <td>${item.food.foodPrice}</td>
                        <td>${item.quantity}</td>
                        <%--<td>￥<fmt:formatNumber value="${item.subtotal}" pattern="#.00"/></td>--%>
                        <td>￥${item.subtotal}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="4" align="right">￥${total}</td>
                </tr>
                <!--   显示内容结束  -->
            </TABLE>
            <a href="foods/list.do" style="font-size: 20px;">继续购物</a>

        </TD>
    </TR>
    </TBODY>
</TABLE>
<jsp:include page="BodyFoot.jsp" />
</BODY>
</HTML>
