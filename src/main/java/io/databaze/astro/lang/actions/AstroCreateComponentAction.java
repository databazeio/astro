package io.databaze.astro.lang.actions;

import java.util.Map;

import javax.swing.Icon;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsActions;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import icons.AstroIcons;
import io.databaze.astro.lang.AstroFileType;

public class AstroCreateComponentAction extends CreateFileFromTemplateAction implements DumbAware {

    public static final String NEW_ASTRO_FILE = "New Astro File";
    private static final String ASTRO_FILE = "Astro Template";

    public AstroCreateComponentAction() {
        super(NEW_ASTRO_FILE, "Astro file", AstroIcons.Astro);
    }

    @Override
    protected void buildDialog(final @NotNull Project project, final @NotNull PsiDirectory directory, final CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle(NEW_ASTRO_FILE)
               .addKind(NEW_ASTRO_FILE, AstroIcons.Astro, ASTRO_FILE);
    }

    @Override
    protected String getActionName(final PsiDirectory directory, final @NotNull String newName, final String templateName) {
        return NEW_ASTRO_FILE;
    }

    @Override
    protected void postProcess(final @NotNull PsiFile createdElement, final String templateName, final Map<String, String> customProperties) {
        if (createdElement instanceof AstroFileType) {
            final Project project = createdElement.getProject();
            final Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
            if (editor == null) {
                return;
            }
            final VirtualFile virtualFile = createdElement.getContainingFile().getVirtualFile();
            if (virtualFile == null) {
                return;
            }
            final PsiElement lastChild = createdElement.getLastChild();
            if (FileDocumentManager.getInstance().getDocument(virtualFile) == editor.getDocument()) {
                editor.getCaretModel().moveToOffset(lastChild.getTextRange().getEndOffset());
            }
        }
    }
}
