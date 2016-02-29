<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dataType</title>
</head>
<body>
int:${i}
double:${d}
boolean:${b?c}
date:${date?string("yyyy-MM-dd hh:mm:ss")}
string:${s}
javabean:${depart.id},${depart.name}
list:
<#list depart.employees as emp>
	${emp.name}--${emp.age}--${emp.sex}
</#list>
map:
<#list map?keys as key>
	${key} -- ${map[key]}
</#list>

</body>
</html>