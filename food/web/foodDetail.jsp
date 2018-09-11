<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            <div id="detail">
                <h3>${food.foodName}</h3>
                <span>
						<img src="images/${food.foodImage}" alt="">
					</span>
                <span>
						编号：${food.foodId}<br><br>
						价格：${food.foodPrice}<br><br>
						介绍：${food.description}<br><br>
                </span>
            </div>

        </TD>
    </TR>
    </TBODY>
</TABLE>
<jsp:include page="BodyFoot.jsp" />
</BODY>
</HTML>
