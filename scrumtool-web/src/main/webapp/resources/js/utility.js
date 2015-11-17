//Fonctions pour gérer les boutons (voir le détail ou modifier)
function viewModal() {
	// affiche et cachage des boutons
	$("#editBtn").show();
	$("#closeBtn").show();
	$("#saveModifications").hide();
	$("#cancelBtn").hide();
	$(".modalStatusInfo").hide();
	// peut pas écrire
	$("#storypointInput").prop("disabled", true);
	$("#descriptionInput").prop("disabled", true);
	$("#titleInput").prop("disabled", true);
};

function editModal() {
	// affichage et cachage des boutons
	console.log("In the function editModal");
	$("#saveModifications").show();
	$("#cancelBtn").show();
	$("#editBtn").hide();
	$("#closeBtn").hide();
	console.log("<3<3<3");
	// peut ecrire
	$("#storypointInput").prop("disabled", false);
	$("#descriptionInput").prop("disabled", false);
	$("#titleInput").prop("disabled", false);
}