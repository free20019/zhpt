
var configDriver = {"name":"driver",
		"temp_parameter":"","url":"driver/get",
		"title":[
		         {'col':'name','name':'姓名','styleTitle':'width:5%','style':'text-align:center'}
		         ,{'col':'sfz','name':'身份证','styleTitle':'width:8%','style':'text-align:center'}
		         ,{'col':'sim','name':'手机','styleTitle':'width:8%','style':'text-align:center'}
		         ,{'col':'card','name':'银行卡','styleTitle':'width:8%','style':'text-align:center'}
		     	,{'col':'baId','name':'总公司','styleTitle':'width:10%','style':'text-align:center'}
		     	,{'col':'compId','name':'分公司','styleTitle':'width:10%'}
             	]}


var configUsers = {"name":"users",
		"temp_parameter":"","url":"users/get",
		"title":[
		         {'col':'userName','name':'姓名','styleTitle':'width:10%','style':'text-align:center'}
		         ,{'col':'tel','name':'手机','styleTitle':'width:8%','style':'text-align:center'}
		     	,{'col':'baId','name':'总公司','styleTitle':'width:10%','style':'text-align:center'}
		     	,{'col':'compId','name':'分公司','styleTitle':'width:10%','style':'text-align:center'}
             	]}
var configVehicle = {"name":"vehicle",
		"temp_parameter":"","url":"vehicle/get",
		"title":[
		         {'col':'no','name':'车号','styleTitle':'width:12%','style':'text-align:center'}
		     	,{'col':'baId','name':'总公司','styleTitle':'width:10%','style':'text-align:center'}
		     	,{'col':'compId','name':'分公司','styleTitle':'width:12%','style':'text-align:center'}
		     	,{'col':'remark','name':'备注','styleTitle':'width:12%'}
             	]}

var configYj= {"name":"yj",
		"temp_parameter":"","url":"yj/get",
		"title":[
		         {'col':'startTime','name':'开始时间','styleTitle':'width:12%','style':'text-align:center','type':'date'}
		         ,{'col':'endTime','name':'结束时间','styleTitle':'width:8%','style':'text-align:center','type':'date'}
		     	,{'col':'price','name':'价格','styleTitle':'width:5%','style':'text-align:center'}
             	]
		,"toolbar":"add"}
var configYh = {"name":"yh",
		"temp_parameter":"","url":"yh/get",
		"title":[
				{'col':'type','name':'车型','styleTitle':'width:12%','style':'text-align:center'}
				,{'col':'yh','name':'油耗（百公里）','styleTitle':'width:5%','style':'text-align:center'}
             	]}
var configBtjzj = {"name":"btjzj",
		"temp_parameter":"","url":"btjzj/get",
		"title":[
		         {'col':'startTime','name':'开始时间','styleTitle':'width:12%','style':'text-align:center','type':'date'}
		         ,{'col':'endTime','name':'结束时间','styleTitle':'width:8%','style':'text-align:center','type':'date'}
		     	,{'col':'btjzj','name':'补贴基准价','styleTitle':'width:5%','style':'text-align:center'}
             	]
		,"toolbar":"add"}

var configDriveryd = {"name":"driveryd",
		"temp_parameter":"","url":"driveryd/get",
		"title":[
		         {'col':'name','name':'姓名','styleTitle':'width:12%','style':'text-align:center'}
		         ,{'col':'sfz','name':'身份证','styleTitle':'width:8%','style':'text-align:center'}
		         ,{'col':'sim','name':'手机','styleTitle':'width:8%','style':'text-align:center'}
		         ,{'col':'card','name':'银行卡','styleTitle':'width:12%','style':'text-align:center'}
		     	,{'col':'baId','name':'总公司','styleTitle':'width:5%','style':'text-align:center'}
		     	,{'col':'compId','name':'分公司','styleTitle':'width:12%'}
		     	,{'col':'ydTime','name':'异动时间','styleTitle':'width:12%','type':'date'}
		     	,{'col':'ydRemark','name':'异动原因','styleTitle':'width:12%'}
             	]}
var configVehicleyd = {"name":"vehicleyd",
		"temp_parameter":"","url":"vehicleyd/get",
		"title":[
		         {'col':'no','name':'车号','styleTitle':'width:12%','style':'text-align:center'}
		     	,{'col':'baId','name':'总公司','styleTitle':'width:5%','style':'text-align:center'}
		     	,{'col':'compId','name':'分公司','styleTitle':'width:12%'}
		     	,{'col':'ydTime','name':'异动时间','styleTitle':'width:12%','type':'date'}
		     	,{'col':'ydRemark','name':'异动原因','styleTitle':'width:12%'}
             	]}
var configUsersyd = {"name":"usersyd",
		"temp_parameter":"","url":"usersyd/get",
		"title":[
		         {'col':'userName','name':'姓名','styleTitle':'width:12%','style':'text-align:center'}
		         ,{'col':'tel','name':'手机','styleTitle':'width:8%','style':'text-align:center'}
		     	,{'col':'baId','name':'总公司','styleTitle':'width:5%','style':'text-align:center'}
		     	,{'col':'compId','name':'分公司','styleTitle':'width:12%'}
		     	,{'col':'ydTime','name':'异动时间','styleTitle':'width:12%','type':'date'}
		     	,{'col':'ydRemark','name':'异动原因','styleTitle':'width:12%'}
             	]}
var configBtjs = {"name":"btjs",
		"temp_parameter":"","url":"bcb/get",
		"title":[
		         {'col':'driverId','name':'司机','styleTitle':'width:5%','style':'text-align:center'}
		         ,{'col':'no','name':'车号','styleTitle':'width:8%','style':'text-align:center'}
		         ,{'col':'datetime','name':'日期','styleTitle':'width:10%','style':'text-align:center','type':'date'}
		         ,{'col':'bc','name':'班次','styleTitle':'width:5%','style':'text-align:center'}
		     	,{'col':'bl','name':'比例','styleTitle':'width:5%','style':'text-align:center'}
		     	,{'col':'yj','name':'油价（元）','styleTitle':'width:5%','style':'text-align:center'}
		     	,{'col':'btjzj','name':'补贴基准价（元）','styleTitle':'width:7%','style':'text-align:center'}
		     	,{'col':'yh','name':'油耗（百公里）','styleTitle':'width:7%','style':'text-align:center'}
		     	,{'col':'lc','name':'里程（每日）','styleTitle':'width:7%','style':'text-align:center'}
		     	,{'col':'je','name':'补贴金额（元）','styleTitle':'width:7%','style':'text-align:center'}
             	]}
var configBtjsmon = {"name":"btjsmon",
		"temp_parameter":"","url":"common/getMon",
		"title":[
		         {'col':'DRIVER_ID','name':'司机','styleTitle':'width:5%','style':'text-align:center'}
		         ,{'col':'MON','name':'日期（月）','styleTitle':'width:8%','style':'text-align:center'}
		     	,{'col':'JE','name':'补贴金额（元）','styleTitle':'width:12%'}
             	]}
var configBcb = {"name":"bcb",
		"temp_parameter":"","url":"bcb/get",
		"title":[
		         {'col':'no','name':'车号','styleTitle':'width:12%','style':'text-align:center'}
		         ,{'col':'datetime','name':'日期','styleTitle':'width:8%','style':'text-align:center','type':'date'}
		         ,{'col':'driverId','name':'司机','styleTitle':'width:5%','style':'text-align:center'}
		         ,{'col':'bc','name':'班次','styleTitle':'width:5%','style':'text-align:center'}
		     	,{'col':'bl','name':'比例','styleTitle':'width:12%'}
             	]}
var configBt = {"name":"bt",
		"temp_parameter":"","url":"bt/get",
		"title":[
		         {'col':'driverId','name':'姓名','styleTitle':'width:12%','style':'text-align:center'}
		         ,{'col':'datetime','name':'日期','styleTitle':'width:8%','style':'text-align:center','type':'date'}
		     	,{'col':'bc','name':'班次','styleTitle':'width:5%','style':'text-align:center'}
		     	,{'col':'je','name':'补贴金额','styleTitle':'width:12%'}
		     	,{'col':'tel','name':'电话','styleTitle':'width:12%'}
             	]}

