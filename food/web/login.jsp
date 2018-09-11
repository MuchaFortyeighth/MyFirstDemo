<%@ page language="java" pageEncoding="utf-8"%>

<HTML>
<HEAD>
<TITLE>我学我会网上订餐系统</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/styles.css" type="text/css" />

<script language="javascript" type="text/javascript" src="js/javaScript.js"></script>

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
							<TBODY>
								<TR bgColor=#dadada>
									<TD width="100%" align="center">我学我会网上点餐系统用户请直接登录</TD>
								</TR>
							</TBODY>
						</TABLE>
						<BR>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			<!-- 用loginForm表单向checkLogin.jsp用POST请求提交数据，注意method属性和action属性的设置
				loginName参数用来保存用户名 
				loginPass参数用来保存密码
			-->
			<form method="POST" name="loginForm" action="user/login.do">
			<table width="100%" border="0">
				<tr>
					<td width="15%">&nbsp;</td>
					<td width="12%">&nbsp;</td>
					<td width="29%">&nbsp;</td>
					<td width="44%">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="center">用户名：</td>
					<td valign="top"><input type="text" name="userName" size="19"
						class="input"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="center">密&nbsp;&nbsp;码：</td>
					<td valign="top"><input type="password" name="password"
						size="20" class="input"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="center"><input type="submit"
						name="Submit" value="登录"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="33">&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</form>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<jsp:include page="BodyFoot.jsp" />
</BODY>
</HTML>
