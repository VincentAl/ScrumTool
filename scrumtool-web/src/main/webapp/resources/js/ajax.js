var changeStoryCategory = function(storyId, columnId) {
	$.ajax({
		url : "story/" + storyId + "/column/" + columnId,
		method : "PUT",
		success : function() {
			console.log("ok");
		}
	});
};

var saveStory = function(story) {
	$.ajax({
		url : "story/" + story.id,
		method : "PATCH",
		data : JSON.stringify(story),
		dataType : "json",
		contentType : "application/json",
		success : function(data, status, req) {
			console.log("loool")
			$(".bg-success").slideToggle();
			setTimeout(function() {
				$("#detailsModal").modal('hide');
			}, 800);
		},
		error : function(req, status, error) {
			console.log("error", error);
			$(".bg-danger").slideToggle();
		}
	});
};

var showDetailsStory = function(id) {
	
};