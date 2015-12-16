$(function() {

	dragula(
			[document.getElementById('story_column_1'),
					document.getElementById('story_column_2'),
					document.getElementById('story_column_3')]).on(
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
			[document.getElementById('task_column_1'),
					document.getElementById('task_column_2'),
					document.getElementById('task_column_3')]).on(
			'drop',
			function(elem, target, source, sib) {
				if (sib != null) {
					changeTaskCategory($(elem).data('taskid'), $(target)
							.data('taskcolumnid'), $(sib).data('taskid'));
				} else {
					changeTaskCategory($(elem).data('taskid'), $(target)
							.data('taskcolumnid'), -1);
				}
			});
	
	

	$("#toggleBtn").click(
			function() {
				$(".addForm").slideToggle();
				$("#toggleBtn span").toggleClass(
						"glyphicon-chevron-right glyphicon-chevron-down");
			});

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

	// change les boutons pour la modification de story
	$("#editBtn").click(function(event) {
		editModal();
	});

	// Shortcut for opening the form
	$(document).keydown(function(e) {
	    if (e.keyCode == 65 && e.shiftKey) {
	        $('#add').click();
	        setTimeout(sexyTroll,1000);
	    }
	});
	
	$('#add').click(function() {
		$('#addFormu').fadeToggle();
	});

	$('.addTask').click(function(e) {
		var idStory = $(this).attr("identif");
		$('.addFormuTask').fadeToggle();
		$('.' + idStory).fadeToggle();	
		e.stopPropagation();
	});
	
	// Shortcut for opening the form
	$(document).keydown(function(e) {
	    if (e.keyCode == 84 && e.shiftKey) {
	        $('.addFormuTask').click();
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
	//function sexyTroll(){
	//var div = $('<div/>').addClass("sexyTroll");
	//$('body').append(div);
	//}
	
	window.setTimeout(function(){
		$('#message_helper').css('visibility', 'hidden');
	}, 3000);
	
	//affichage détails story dans sprint
	$('.drag-item').click(function(){
		
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
	})
});
