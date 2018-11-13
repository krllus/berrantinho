package com.example.joao.berrantinho.features.file;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.joao.berrantinho.BaseFragment;
import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.adapter.NotesAdapter;
import com.example.joao.berrantinho.model.Note;
import com.example.joao.berrantinho.model.RelatorioDocument;
import com.example.joao.berrantinho.utils.BioxStorageUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilesFragment extends BaseFragment {

  private static Pattern pattern = Pattern.compile("(\\d+)(?!.*\\d).pdf$");
  //creation of note;
  private EditText edtNoteMessage;
  private Button btnCreateNote;
  private NotesAdapter notesAdapter;
  private Button btnShareNotes;

  public static FilesFragment newInstance() {
    return new FilesFragment();
  }

  @Override
  public int getFragmentLayout() {
    return R.layout.fragment_file;
  }

  @Override
  public void setUpCustomViews(View rootView) {
    edtNoteMessage = rootView.findViewById(R.id.edt_note_content);
    btnCreateNote = rootView.findViewById(R.id.btn_create_note);
    btnShareNotes = rootView.findViewById(R.id.button_share_notes);

    notesAdapter = new NotesAdapter();

    RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(notesAdapter);
  }

  @Override
  public void setUpLayoutListeners() {
    btnCreateNote.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        proccessNoteCreation();
      }
    });

    btnShareNotes.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        exportToEmail();
      }
    });

    updateUI();
  }

  @Override
  public void initDependencies() {

  }

  @Override
  public void updateUI() {
    ArrayList<Note> notes = new ArrayList<>();

    File notesDir = BioxStorageUtils.getDirectoryForDocumentType(new RelatorioDocument());
    Matcher matcher;
    for (File f : notesDir.listFiles()) {
      String fileName = f.getName();
      matcher = pattern.matcher(fileName);
      if (matcher.find()) {
        Note n = new Note(f.getName(), f);
        notes.add(n);
      }
    }

    notesAdapter.updateElements(notes);
  }

  private void proccessNoteCreation() {
    String noteMessage = edtNoteMessage.getText().toString();
    if (noteMessage.trim().isEmpty()) return;

    RelatorioDocument relatorioDocument = new RelatorioDocument();
    relatorioDocument.setMessage(noteMessage);

    boolean fileCreated = BioxStorageUtils.createDocument(relatorioDocument);
    if (fileCreated) {
      //clear edt text
      edtNoteMessage.setText("");
      updateUI();
      Toast.makeText(getContext(), "arquivo criado", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(getContext(), "arquivo nao pode ser criado", Toast.LENGTH_SHORT).show();
    }
  }

  private void exportToEmail() {

    Context context = getActivity();
    if (context == null) {
      Log.e("TAG", "Context is null");
      return;
    }

    Intent emailSelectorIntent = new Intent(Intent.ACTION_SEND);
    emailSelectorIntent.setType("text/plain");

    Intent sendEmailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
    sendEmailIntent.setType("message/rfc822");
    sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { getMailTo() });
    sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, getEmailSubject());
    sendEmailIntent.putExtra(Intent.EXTRA_TEXT, getEmailBody());
    sendEmailIntent.setSelector(emailSelectorIntent);

    //attachment list
    ArrayList<Uri> uris = new ArrayList<>(getAttachmentsURI(context));
    if (uris.isEmpty()) return;

    List<ResolveInfo> resInfoList = context.getPackageManager()
        .queryIntentActivities(sendEmailIntent, PackageManager.MATCH_DEFAULT_ONLY);
    //set file provider permission
    for (ResolveInfo resolveInfo : resInfoList) {
      for (Uri uri : uris) {
        String packageName = resolveInfo.activityInfo.packageName;
        context.grantUriPermission(packageName, uri,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Log.d("email tag", "email_file_uri" + uri.toString());
      }
    }

    sendEmailIntent.putExtra(Intent.EXTRA_STREAM, uris);
    //sendEmailIntent.putExtra(Intent.EXTRA_STREAM, uris.get(0));
    //sendEmailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

    startActivity(Intent.createChooser(sendEmailIntent, "Selecione um cliente"));
  }

  private List<Uri> getAttachmentsURI(Context context) {
    List<Uri> attachementList = new ArrayList<>();

    for (Note note : notesAdapter.getElements()) {
      if (note.isSelected()) {
        attachementList.add(BioxStorageUtils.getUriForFile(context, note.getFile()));
      }
    }

    return attachementList;
  }

  private String getMailTo() {
    return "joaopepekaslayer69@gmail.com";
  }

  private String getEmailSubject() {
    return "Email Subject";
  }

  private String getEmailBody() {
    return "Email Body";
  }
}
