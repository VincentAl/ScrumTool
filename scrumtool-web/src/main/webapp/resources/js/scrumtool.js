$(function(){
	
	dragula([
		document.getElementById('story_column_1'),
		document.getElementById('story_column_2'),
		document.getElementById('story_column_3')
	])
		.on('drop', function(elem, target){
			changeStoryCategory($(elem).data('storyid'), $(target).data('storycolumnid'));
		});
	
	$("#toggleBtn").click(function() {
		$(".addForm").slideToggle();
		$("#toggleBtn span").toggleClass("glyphicon-chevron-right glyphicon-chevron-down");
	});
	
	$('#detailsModal').on('show.bs.modal', function(event){
		var thisStory = $(event.relatedTarget) // item that triggered the event
		var id = thisStory.data('storyid') // Extract info from data-idstory
								console.log(event)// attributes
		viewModal();
		//showDetailsStory(id);
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
	
	//Suppression d'une story
	$(".btnSup").click(function(){
		var id = $(this).attr('id');
		var $that = $(this).parent();

		$.ajax({
			url : "story/"+id,
			type : "DELETE",
			success : function(){
				$that.remove();
			},
		});
	});
	
	
	//change les boutons pour la modification de story
	$("#editBtn").click(function(event){
		editModal();
	});
	
	  $('#add').click(function() {
		    $('#addFormu').fadeToggle();
		  })
		  
		  // Remove pop up when you click around it
		  $(document).mouseup(function (e) {
		    var container = $("#addFormu");

	//enregistre la ou les modifications apportées
	$("#saveModifications").click(function(){
		var id = $('#idStoryInput').val();
		var title = $('#titleInput').val();
		var description = $('#descriptionInput').val();
		var storyPoint = $('#storypointInput').val();
		var story = {title:title,id:id,description:description, storyPoints:storyPoint};
		saveStory(story);
	});
	
	$("#cancelBtn").click(function(){
		//showDetailsStory($('#idStoryInput').val());
		
	});

});

