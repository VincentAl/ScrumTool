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

		createNewTask(idStory, taskTitle, taskDescription);
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
	$(".btnSupT").click(function(e) {
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
	$(document).keydown(function(e) {
		if (e.keyCode == 84 && e.shiftKey) {
			$('.addTask').click();
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
	});

	// function sexyTroll(){
	// var div = $('<div/>').addClass("sexyTroll");
	// $('body').append(div);
	// }

	window.setTimeout(function() {
		$('#message_helper').css('visibility', 'hidden');
	}, 3000);

	// affichage détails story dans sprint
	$('.drag-item').click(function() {

		$('#detailStory').show(500)
		var id = $(this).data('storyid')

		$.ajax({
			url : "story/" + id,
			type : "GET",
			success : function(story) {
				// Update the modal's content.
				$('#storyTitle').html(story.title);
				$('#storyDescription').html(story.description);
				$('#storyPoint').html(story.storyPoints);
			}
		});
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
	//association des tâches et des story avec les couleurs
		//pour les story
	$(document).ready(function(){
		$('.story-card').each(function(){
			var id = $(this).data('storyid');
				switch(id%12){
				case 1%12:
					//$(this).css('color', 'rgb(237,28,36)');//red
					$(this).css('background-color', 'rgba(237,28,36,0.55)');
					break;
				case 2%12:
					//$(this).css('color', 'rgb(255,127,39)');//orange
					$(this).css('background-color', 'rgba(255,127,39,0.55)');
					break;
				case 3%12:
					//$(this).css('color', 'rgb(255,242,0)');//yellow
					$(this).css('background-color', 'rgba(255,242,0,0.55)');
					break;
				case 4%12:
					//$(this).css('color', 'rgb(34,177,76)');//green
					$(this).css('background-color', 'rgba(34,177,76,0.55)');
					break;
				case 5%12:
					//$(this).css('color', 'rgb(0,162,232)');//cyan
					$(this).css('background-color', 'rgba(0,255,255,0.55)');
					break;
				case 6%12:
					//$(this).css('color', 'rgb(63,72,204)');//blue
					$(this).css('background-color', 'rgba(63,72,204,0.55)');
					break;
				case 7%12:
					//$(this).css('color', 'rgb(163,73,164)');//purple
					$(this).css('background-color', 'rgba(163,73,164,0.55)');
					break;
				case 8%12:
					//$(this).css('color', 'rgb(255,0,128)');//pink
					$(this).css('background-color', 'rgba(255,0,128,0.55)');
					break;
				case 9%12:
					//$(this).css('color', 'rgb(255,201,14)');//ocra
					$(this).css('background-color', 'rgba(255,201,14,0.55)');
					break;
				case 10%12:
					//$(this).css('color', 'rgb(181,230,29)');//lime
					$(this).css('background-color', 'rgba(9,106,9,0.55)');
					break;
				case 11%12:
					//$(this).css('color', 'rgb(185,122,87)');//brown
					$(this).css('background-color', 'rgba(185,122,87,0.55)');
					break;
				case 12%12:
					//$(this).css('color', 'rgb(136,0,21)');//bordeau
					$(this).css('background-color', 'rgba(136,0,21,0.55)');
					break;
				}
		});
	});
		//pour les taches
	$(document).ready(function(){
		$('.task-card').each(function(){
			var id = $(this).data('storyid');
				switch(id%12){
				case 1%12:
					//$(this).css('color', 'rgb(237,28,36)');//red
					$(this).css('background-color', 'rgba(237,28,36,0.55)');
					break;
				case 2%12:
					//$(this).css('color', 'rgb(255,127,39)');//orange
					$(this).css('background-color', 'rgba(255,127,39,0.55)');
					break;
				case 3%12:
					//$(this).css('color', 'rgb(255,242,0)');//yellow
					$(this).css('background-color', 'rgba(255,242,0,0.55)');
					break;
				case 4%12:
					//$(this).css('color', 'rgb(34,177,76)');//green
					$(this).css('background-color', 'rgba(34,177,76,0.55)');
					break;
				case 5%12:
					//$(this).css('color', 'rgb(0,162,232)');//cyan
					$(this).css('background-color', 'rgba(0,255,255,0.55)');
					break;
				case 6%12:
					//$(this).css('color', 'rgb(63,72,204)');//blue
					$(this).css('background-color', 'rgba(63,72,204,0.55)');
					break;
				case 7%12:
					//$(this).css('color', 'rgb(163,73,164)');//purple
					$(this).css('background-color', 'rgba(163,73,164,0.55)');
					break;
				case 8%12:
					//$(this).css('color', 'rgb(255,0,128)');//pink
					$(this).css('background-color', 'rgba(255,0,128,0.55)');
					break;
				case 9%12:
					//$(this).css('color', 'rgb(255,201,14)');//ocra
					$(this).css('background-color', 'rgba(255,201,14,0.55)');
					break;
				case 10%12:
					//$(this).css('color', 'rgb(181,230,29)');//lime
					$(this).css('background-color', 'rgba(9,106,9,0.55)');
					break;
				case 11%12:
					//$(this).css('color', 'rgb(185,122,87)');//brown
					$(this).css('background-color', 'rgba(185,122,87,0.55)');
					break;
				case 12%12:
					//$(this).css('color', 'rgb(136,0,21)');//bordeau
					$(this).css('background-color', 'rgba(136,0,21,0.55)');
					break;
				}
		});
	});
});



