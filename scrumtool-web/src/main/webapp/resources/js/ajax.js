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
	$.ajax({
		url : "new-task/",
		method : "POST",
		data : JSON.stringify({'idStory': idStory, 'taskTitle': taskTitle, 'taskDescription': taskDescription}),
		dataType : "json",
		contentType : "application/json",
		success : function(data, status, req) {
			console.log("Successfully saved task !")
			$('#task_column_1').append(
				'<li class="drag-item task-card" data-taskid="'+data.id+'" data-storyid="'+idStory+'">'+
				'<span class="drag-title" >'+taskTitle+'</span>'+
				'<button type="button" class="close btnSup" id="task_'+data.id+'" data-dismiss="alert" aria-label="Close">'+
				'<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></li>'
			);
			$('#addTaskModal').modal('hide').find('form')[0].reset();
			putColorOn('.task-card');
			
		},
		error : function(req, status, error) {
			console.log("error", error);
			//$(".bg-danger").slideToggle();
		}
	});	
};

var showDetailsStory = function(id) {	
};

//association des tâches et des story avec les couleurs
//pour les story
$(document).ready(function(){
	putColorOn('.story-card');
});

//pour les taches
$(document).ready(function(){
	putColorOn('.task-card');
});
var putColorOn = function(className){
	$(className).each(function(){
	var id = $(this).data('storyid');
		switch(id%12){
		case 1%12:
			//red
			$(this).css('background-color', 'rgba(237,28,36,0.55)');
			break;
		case 2%12:
			//orange
			$(this).css('background-color', 'rgba(255,127,39,0.55)');
			break;
		case 3%12:
			//yellow
			$(this).css('background-color', 'rgba(255,242,0,0.55)');
			break;
		case 4%12:
			//green
			$(this).css('background-color', 'rgba(34,177,76,0.55)');
			break;
		case 5%12:
			//cyan
			$(this).css('background-color', 'rgba(0,255,255,0.55)');
			break;
		case 6%12:
			//blue
			$(this).css('background-color', 'rgba(63,72,204,0.55)');
			break;
		case 7%12:
			//purple
			$(this).css('background-color', 'rgba(163,73,164,0.55)');
			break;
		case 8%12:
			//pink
			$(this).css('background-color', 'rgba(255,0,128,0.55)');
			break;
		case 9%12:
			//ocra
			$(this).css('background-color', 'rgba(255,201,14,0.55)');
			break;
		case 10%12:
			//lime
			$(this).css('background-color', 'rgba(9,106,9,0.55)');
			break;
		case 11%12:
			//brown
			$(this).css('background-color', 'rgba(185,122,87,0.55)');
			break;
		case 12%12:
			//bordeau
			$(this).css('background-color', 'rgba(136,0,21,0.55)');
			break;
		}	
	});
};