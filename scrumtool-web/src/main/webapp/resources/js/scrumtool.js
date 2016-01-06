$(function() {

	dragula(
			[ document.getElementById('story_column_1'),
					document.getElementById('story_column_2'),
					document.getElementById('story_column_3') ]).on(
			'drop',
			function(elem, target, source, sib) {
				if (sib != null) {
					changeStoryCategory($(elem).data('storyid'), $(target)
							.data('storycolumnid'), $(sib).data('storyid'));
				} else {
					changeStoryCategory($(elem).data('storyid'), $(target)
							.data('storycolumnid'), -1);
				}
			});

	dragula(
			[ document.getElementById('task_column_1'),
					document.getElementById('task_column_2'),
					document.getElementById('task_column_3') ]).on(
			'drop',
			function(elem, target, source, sib) {
				if (sib != null) {
					changeTaskCategory($(elem).data('taskid'), $(target).data(
							'taskcolumnid'), $(sib).data('taskid'));
				} else {
					changeTaskCategory($(elem).data('taskid'), $(target).data(
							'taskcolumnid'), -1);
				}
			});

	$("#toggleBtn").click(
			function() {
				$(".addForm").slideToggle();
				$("#toggleBtn span").toggleClass(
						"glyphicon-chevron-right glyphicon-chevron-down");
			});

	// Modal modification
	$('#detailsModal').on('show.bs.modal', function(event) {
		var thisStory = $(event.relatedTarget) // item that triggered the event
		var id = thisStory.data('storyid') // Extract info from data-idstory
		console.log(event)// attributes
		viewModal();
		// showDetailsStory(id);
		$.ajax({
			url : "story/" + id,
			type : "GET",
			success : function(story) {
				// Update the modal's content.
				$('#detailsModal').find('.modal-title').html('D&eacute;tails');
				$('#titleInput').val(story.title);
				$('#descriptionInput').val(story.description);
				$('#storypointInput').val(story.storyPoints);
				$('#idStoryInput').val(story.id);
			}
		});
	});

	// Modal for tasks
	$('#addTaskModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var idStory = button.data('idstory');
		var modal = $(this);
		modal.find('#addTask_idStory').val(idStory);
	}).on('hidden.bs.modal', function(event){
		$('#addTaskModal').find('form')[0].reset();
	});
	
	//Submit du formulaire d'ajout d'une tâche
	$('#addNewTaskForm').submit(function(e){
		console.log("submit form add task")
		e.preventDefault();
		var idStory = $(this).find('#addTask_idStory').val();
		var taskTitle = $(this).find('#addTask_title').val();
		var taskDescription = $(this).find('#addTask_description').val();
		var idUser = $(this).find('#addTask_idUser').val();

		createNewTask(idStory, taskTitle,taskDescription, idUser) ;
		return false;
	});

	// Suppression d'une story
	$(".btnSup").click(function(e) {
		e.stopPropagation();
		var id = $(this).attr('id');
		var $that = $(this).parent();

		$.ajax({
			url : "story/" + id,
			type : "DELETE",
			success : function() {
				$that.remove();
			},
		});
	});
	
	//Suppression d'une tâche 
	$(".drag-inner-list").on('click', '.btnSupT',function(e) {
		e.stopPropagation();
		var taskId=$(this).attr('id')
		var id = taskId.substr(5,5);
		var $that = $(this).parent();

		$.ajax({
			url : "task/" + id,
			type : "DELETE",
			success : function() {
				$that.remove();
			},
		});
	});
	
	
	

	// change les boutons pour la modification de story
	$("#editBtn").click(function(event) {
		editModal();
	});

	// Shortcut for opening the form
	$(document).keydown(function(e) {
		if (e.keyCode == 65 && e.shiftKey) {
			$('#add').click();
			setTimeout(sexyTroll, 1000);
		}
	});

	$('#add').click(function() {
		$('#addFormu').fadeToggle();
	});
	
	// bouton ajout tache
	$('.btn-add-task').on('click', function(e){
		//e.stopPropagation();
		console.log("btn-add-tasSk");
	});
	

	// Shortcut for opening the form
	$(document).keydown(function(evt) {
		if (evt.keyCode == 84 && (evt.shiftKey)) {
			evt.preventDefault();
			$('#addTaskModal').modal();
		}
	});

	// Remove pop up when you click around it
	$(document).mouseup(function(e) {
		var container = $(".addFormuTask");
		if (!container.is(e.target) && container.has(e.target).length === 0) {
			container.fadeOut();
		}
	});

	// Remove pop up when you click around it
	$(document).mouseup(function(e) {
		var container = $("#addFormu");
		if (!container.is(e.target) && container.has(e.target).length === 0) {
			container.fadeOut();
		}
	});

	// enregistre la ou les modifications apportées
	$("#saveModifications").click(function() {
		var id = $('#idStoryInput').val();
		var title = $('#titleInput').val();
		var description = $('#descriptionInput').val();
		var storyPoint = $('#storypointInput').val();
		var story = {
			title : title,
			id : id,
			description : description,
			storyPoints : storyPoint
		};
		saveStory(story);
		$("#cancelEditBtn").click();
		
	});

	// function sexyTroll(){
	// var div = $('<div/>').addClass("sexyTroll");
	// $('body').append(div);
	// }

	window.setTimeout(function() {
		$('#message_helper').hide();
	}, 3000);

	// affichage détails story dans sprint
	$('.story-card').click(function() {

		$("#editStoryBtn").show();
		$("#closeDetailsBtn").show();
		$("#saveModifications").hide();
		$("#cancelEditBtn").hide();
		$('#detailStory').show(500)
		var id = $(this).data('storyid')

		$.ajax({
			url : "story/" + id,
			type : "GET",
			success : function(story) {
				// Update the form's content.
				$('#titleInput').val(story.title);
				$('#descriptionInput').val(story.description);
				$('#storypointInput').val(story.storyPoints);
				$('#idStoryInput').val(story.id);
			}
		});
		
		getTasksByStory(id);
	});
	
	// mise en lumière dans le sprint ! #starlight
	$('.drag-inner-list').on('click', '.task-card, .story-card', function(e){
		starlight($(this).data('storyid'));
	});
	// Remove starlighted tasks
	$(document).mouseup(function(e) {
		var container = $("");
		if (!$.contains($('.starlight'), e.target) && container.has(e.target).length === 0) {
			$('.starlight').removeClass('starlight');
		}
	});

	$('#closeDetailsBtn').click(function(){
		$('#detailStory').hide(500)
	});
	
	$('#editStoryBtn').click(function(){
		$("#editStoryBtn").hide();
		$("#closeDetailsBtn").hide();
		$("#saveModifications").show();
		$("#cancelEditBtn").show();
		
		$("#storypointInput").prop("disabled", false);
		$("#descriptionInput").prop("disabled", false);
		$("#titleInput").prop("disabled", false);
	});
	
	$("#cancelEditBtn").click(function(){
		$("#editStoryBtn").show();
		$("#closeDetailsBtn").show();
		$("#saveModifications").hide();
		$("#cancelEditBtn").hide();
		
		$("#storypointInput").prop("disabled", true);
		$("#descriptionInput").prop("disabled", true);
		$("#titleInput").prop("disabled", true);
	});
		
	//on show popup to close sprint
	$('#closeSprintModal').on('show.bs.modal', function(event) {
		$.ajax({
			url : "getClosedSprintMessage/",
			type : "GET",
			success : function(message) {
				// Update the modal's content.
				$('#closeSprintMessage').html(message.type);
				if(message.id!=3){
					$('closeSprintSubmite').hide();
				}
			}
		});
	});
});





