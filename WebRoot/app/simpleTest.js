if(typeof(dojoConfig) == undefined ){
	dojoConfig = {
			 packages : [ {
				name : "app",
				location : "/rybt/app"
			} ]
	};
	console.log('#####simpleTest');
	if(typeof(dojo) == undefined ){
		 console.log('#####simpleTest dojo');
		 document.write('<script type="text/javascript" src="/rybt/libs/dojo-release-1.10.4-src/dojo/dojo.js"></script>');
	}
}
