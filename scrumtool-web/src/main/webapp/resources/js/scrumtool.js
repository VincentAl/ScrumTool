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
	
	$('#detailsModal').on('show.bs.modal', function (event) {
		  var thisStory = $(event.relatedTarget) // item that triggered the event
		  var id = thisStory.data('storyid') // Extract info from data-idstory attributes
		  var modal = $(this)
		  $.ajax({
			url : "story/"+id,
			type : "GET",
			success : function(story){
				// Update the modal's content.
				modal.find('.modal-title').html('D&eacute;tails')
				$('#titleInput').val(story.title)
				$('#descriptionInput').val(story.description)
				$('#storypointInput').val(story.storyPoints)
			}
		})
	})
	
	$(".btnSup").click(function(){
		var id = $(this).attr('id');
		var $that=$(this).parent();

		$.ajax({
			url : "story/"+id,
			type : "DELETE",
			success : function(){
				$that.remove();
			}
		})
		
	});
	
	  $('#add').click(function() {
		    $('#addFormu').fadeToggle();
		  })
		  
		  // Remove pop up when you click around it
		  $(document).mouseup(function (e) {
		    var container = $("#addFormu");

		    if (!container.is(e.target)
		        && container.has(e.target).length === 0)
		    {
		        container.fadeOut();
		    }
		  });
	
});

