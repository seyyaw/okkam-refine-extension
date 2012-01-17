
$(function() {	 

$(document).ready(function() {
 
	var dialog = $(DOM.loadHTML("ENSUploader", "html/login.html"));
	var elmts = DOM.bind(dialog);

 	elmts.dialogHeader.text("Login to Google Refine");
 	var level = DialogSystem.showDialog(dialog);

	elmts.password.focus();
	
	elmts.dialogframe.css("color","red");
	//elmts.password.css("color","black");
	//elmts.username.css("color","black");
	elmts.dialogBody.css("color","blue");
	
 	elmts.loginButton.click(function() {
		if(elmts.password.val()=="seid"){
			DialogSystem.dismissUntil(level - 1);
		} 					  
 });
 	
 	 	elmts.cancelButton.click(function() {
 	 		$('div').hide();
 });
 	
});

});