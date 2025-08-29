function validateForm(event) {
	const newPassword = document.getElementById("password").value;
	const confirmPassword = document.getElementById("confermaPassword").value;

	// Lunghezza minima
	if (newPassword.length < 8 || confirmPassword.length < 8) {
		alert("La password deve essere lunga almeno 8 caratteri.");
		event.preventDefault(); // Blocca l'invio del form
		return false;
	}

	// Password uguale a conferma
	if (newPassword !== confirmPassword) {
		alert("Password e Conferma Password non coincidono.");
		event.preventDefault();
		return false;
	}

	// Tutto OK
	return true;
}