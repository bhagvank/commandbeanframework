<HTML>
<HEAD>
<TITLE>Check.jsp</TITLE>
</HEAD>
<BODY>
<jsp:useBean id="command" class="cbf.commands.CheckCommandBean" scope="request"/>
Message from the command
<%=command.getMessage()%>
</BODY>
</HTML>
