default security
----------------
simple spring boot project creation - done
	-get and post rest endpoint implementation - done
	-testing 

adding spring security - done
	-using default spring security - username(user) and password(console) - done
	-for post api , disable csrf
	-configuring username and password in properties file - done
		spring.security.user.name=
		spring.security.user.password=
	-testing, (wrong password)
	- solution is enable httpBasic authentication in security config
