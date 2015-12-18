var changeStoryCategory = function(storyId, columnId, previousStoryId) {
	$.ajax({
		url : "story/" + storyId + "/column/" + columnId + "/previous/" + previousStoryId,
		method : "PUT",
		success : function() {
			console.log("Story category changed !");
		}
	});
};

var changeTaskCategory = function(taskId, columnId, previousTaskId) {
	$.ajax({
		url : "task/" + taskId + "/column/" + columnId + "/previous/" + previousTaskId,
		method : "PUT",
		success : function() {
			console.log("Task category changed !");
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


var createNewTask = function(idStory, taskTitle, taskDescription) {
	console.log("chattte")
	$.ajax({
		url : "new-task/",
		method : "POST",
		data : JSON.stringify({'idStory': idStory, 'taskTitle': taskTitle, 'taskDescription': taskDescription}),
		dataType : "json",
		contentType : "application/json",
		success : function(data, status, req) {
			console.log("Successfully saved task !")
			$('#task_column_1').append(
				'<li class="drag-item" data-toggle="modal" data-target="#detailsModal" data-taskid="'+data.id+'">'+
				'<span class="drag-title" >'+taskTitle+'</span>'+
				'<button type="button" class="close btnSup" id="task_'+data.id+'" data-dismiss="alert" aria-label="Close">'+
				'<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></li>'
			);
			$('#addTaskModal').modal('hide');
		},
		error : function(req, status, error) {
			console.log("error", error);
			//$(".bg-danger").slideToggle();
		}
	});
};

var showDetailsStory = function(id) {
	
};