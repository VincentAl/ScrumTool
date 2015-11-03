var changeStoryCategory = function(storyId, columnId){
	
	$.ajax({
		url: "story/"+storyId+"/column/"+columnId,
		method: "PUT",
		success: function(){
			console.log("ok");
		}
	});
}