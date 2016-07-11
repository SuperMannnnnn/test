<%@page import="java.util.*, entity.*" language="java"  contentType="text/xml" pageEncoding="utf-8"%><?xml version="1.0"  encoding="UTF-8"?>
<% //模拟查询数据库获取的List<Flight>  
List<Flight> flights = new ArrayList<Flight>();
flights.add(new Flight("No.100010", "北京", "上海", 200.0, "2016-06-01"));
flights.add(new Flight("No.100011", "北京", "上海", 200.0, "2016-06-02"));
flights.add(new Flight("No.100012", "北京", "上海", 200.0, "2016-06-02"));
flights.add(new Flight("No.100013", "北京", "上海", 200.0, "2016-06-03"));
flights.add(new Flight("No.100014", "北京", "上海", 200.0, "2016-06-03"));
flights.add(new Flight("No.100015", "北京", "上海", 200.0, "2016-06-03"));
flights.add(new Flight("No.100016", "北京", "上海", 200.0, "2016-06-04"));
flights.add(new Flight("No.100017", "北京", "上海", 200.0, "2016-06-04"));
flights.add(new Flight("No.100018", "北京", "上海", 200.0, "2016-06-05"));
flights.add(new Flight("No.100019", "北京", "上海", 200.0, "2016-06-05"));
flights.add(new Flight("No.100020", "上海", "北京", 200.0, "2016-06-01"));
flights.add(new Flight("No.100021", "上海", "北京", 200.0, "2016-06-01"));
flights.add(new Flight("No.100022", "上海", "北京", 200.0, "2016-06-01"));
flights.add(new Flight("No.100023", "上海", "北京", 200.0, "2016-06-02"));
flights.add(new Flight("No.100024", "上海", "北京", 200.0, "2016-06-02"));
flights.add(new Flight("No.100025", "上海", "北京", 200.0, "2016-06-03"));
flights.add(new Flight("No.100026", "上海", "北京", 200.0, "2016-06-04"));
flights.add(new Flight("No.100027", "上海", "北京", 200.0, "2016-06-04"));
flights.add(new Flight("No.100028", "上海", "北京", 200.0, "2016-06-04"));
%>
<%
//把flights中的数据 以xml的方式输出给客户端
%>
<flights>
<%
//获取请求参数
String date = request.getParameter("date");
String number = request.getParameter("number");
for(int i=0; i<flights.size(); i++){ 
		Flight f = flights.get(i);
		if(date!=null && !date.equals(f.getDate())){
			continue;
		}
		if(number!=null && !number.equals(f.getNumber())){
			continue;
		}
%>
	<flight>
		<number><%=f.getNumber()%></number>
		<from><%=f.getFrom() %></from>
		<to><%=f.getTo() %></to>
		<price><%=f.getPrice() %></price>
		<date><%=f.getDate() %></date>
	</flight>
<%} %>
</flights>










    
    
