var upload = function() { 
  Refine.postProcess(
      "ENSUploader",
      "uploadtoENS",
      params = {},
      {},{},
      { 
          "onDone": function(response) {
    	  alert(response["key"]+" Entities are succesfuly created!!!");
    	 }
      }
  );
}

ExporterManager.handlers.showUploaderDialog = function() {
	 var dialog = $(DOM.loadHTML("ENSUploader", "html/uploader-dialog.html"));

	 var elmts = DOM.bind(dialog);
	 elmts.dialogHeader.text("ENS Entity Creator/Uploader");

	 	var level = DialogSystem.showDialog(dialog);
	 	
	 	elmts.uploadButton.click(function() {
		
	 		upload();
	 		DialogSystem.dismissUntil(level - 1);  
		 });
	 elmts.cancelButton.click(function() {
	     DialogSystem.dismissUntil(level - 1);
	  });
};

$(function() {
	ExtensionBar.MenuItems.push({
		"id" : "uploadtoENS",
		"label" : "ENS Uploader",
		"submenu" : [ {
			"id" : "Okkam-storage-upload",
			label : "Upload to OKKAM Storage...",
			click : function() { ExporterManager.handlers.showUploaderDialog(); }
		} ]
	});
});
