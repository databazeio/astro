package io.databaze.astro.lang;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.impl.source.html.HtmlFileImpl;

import io.databaze.astro.lang.psi.AstroElementType;

public class AstroFile extends HtmlFileImpl {


    protected AstroFile(@NotNull final FileViewProvider viewProvider) {
        super(viewProvider, AstroElementType.ASTRO_FILE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return AstroFileType.INSTANCE;
    }
}
