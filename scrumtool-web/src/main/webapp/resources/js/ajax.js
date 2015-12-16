var changeStoryCategory = function(storyId, columnId, previousStoryId) {
	$.ajax({
		url : "story/" + storyId + "/column/" + columnId + "/previous/" + previousStoryId,
		method : "PUT",
		success : function() {
			console.log("ok");
		}
	});
};

var changeTaskCategory = function(storyId, columnId, previousStoryId) {
	$.ajax({
		url : "task/" + storyId + "/column/" + columnId + "/previous/" + previousStoryId,
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
			var title = $('#titleInput').val();
			var storyPoint = $('#storypointInput').val();
			$('[data-storyid = "'+ story.id +'"]').find(".drag-title").text(title);
			$('[data-storyid = "'+ story.id +'"]').find(".badge").text(storyPoint);
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