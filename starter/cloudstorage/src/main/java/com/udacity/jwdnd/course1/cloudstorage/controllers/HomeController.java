package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.exception.CloudStorageException;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialServices;
import com.udacity.jwdnd.course1.cloudstorage.services.FileServices;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteServices;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServices;

@Controller
public class HomeController {

	@Autowired
	private NoteServices noteServices;

	@Autowired
	private FileServices fileServices;

	@Autowired
	private CredentialServices credentialServices;

	@Autowired
	private UserServices userServices;

	@GetMapping({ "", "/", "/home" })
	public String getHomePage(Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		if (username == null) {
			return "redirect:/login";
		}

		Integer userId = userServices.findUserByUsername(username).getUserId();

		model.addAttribute("noteList", noteServices.getAllNotes(userId));
		model.addAttribute("fileList", fileServices.getAllFiles());
		model.addAttribute("credentialList", credentialServices.getAllCredentials(userId));

		return "home";
	}

	@PostMapping("/save-note")
	public String addNote(@ModelAttribute("noteObject") Note note, RedirectAttributes redirectAttributes,
			HttpSession session) {

		String username = (String) session.getAttribute("username");
		if (username == null) {
			return "redirect:/login";
		}

		note.setUserId(userServices.findUserByUsername(username).getUserId());

		noteServices.saveNote(note);
		redirectAttributes.addFlashAttribute("successMessage", "Your changes were successfully saved");

		return "redirect:/result?success";
	}

	@PostMapping("/remove-note/{noteId}")
	public String removeNote(@PathVariable Integer noteId, RedirectAttributes redirectAttributes) {

		noteServices.removeNote(noteId);
		redirectAttributes.addFlashAttribute("successMessage", "Your changes were successfully saved");

		return "redirect:/result?success";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes redirectAttributes,
			HttpSession session) throws IOException {

		if (file.isEmpty()) {
			throw new CloudStorageException("Please select a file to upload");
		}

		if (file.getSize() >= 15728640) {
			throw new CloudStorageException("File size exceeds limit (15MB)");
		}

		String username = (String) session.getAttribute("username");
		if (username == null) {
			return "redirect:/login";
		}

		fileServices.addFile(new File(null, file.getOriginalFilename(), file.getContentType(), file.getSize(),
				userServices.findUserByUsername(username).getUserId(), file.getBytes()));

		redirectAttributes.addFlashAttribute("successMessage", "File uploaded successfully");

		return "redirect:/result?success";
	}

	@PostMapping("/remove-file/{fileId}")
	public String removefile(@PathVariable Integer fileId, RedirectAttributes redirectAttributes) {

		fileServices.removefile(fileId);
		redirectAttributes.addFlashAttribute("successMessage", "Your changes were successfully saved");

		return "redirect:/result?success";
	}

	@PostMapping("/save-credential")
	public String saveCredential(@ModelAttribute("credentialObject") Credential credential,
			RedirectAttributes redirectAttributes, HttpSession session) {

		String username = (String) session.getAttribute("username");
		if (username == null) {
			return "redirect:/login";
		}

		credential.setUserId(userServices.findUserByUsername(username).getUserId());

		credentialServices.addCredential(credential);
		redirectAttributes.addFlashAttribute("successMessage", "Your changes were successfully saved");

		return "redirect:/result?success";
	}

	@PostMapping("/remove-credential/{credentialId}")
	public String removeCredential(@PathVariable Integer credentialId, RedirectAttributes redirectAttributes) {

		credentialServices.removeCredential(credentialId);
		redirectAttributes.addFlashAttribute("successMessage", "Your changes were successfully saved");

		return "redirect:/result?success";
	}

	@GetMapping("/download-file/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Integer fileId) {

		// Retrieve file information from the database.
		File fileEntity = fileServices.getFileById(fileId);

		if (fileEntity == null) {
			throw new CloudStorageException("File not found");
		}

		byte[] fileData = fileEntity.getFileData();

		// Create a Resource object from the file to return to the client.
		ByteArrayResource resource = new ByteArrayResource(fileData);

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"");
		headers.add(HttpHeaders.CONTENT_TYPE, fileEntity.getContentType());
		headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileData.length));

		return ResponseEntity.ok().headers(headers).body(resource);
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?logout";
	}
}
