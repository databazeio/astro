package io.databaze.astro.lang;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.lang.html.HTMLLanguage;
import com.intellij.openapi.fileTypes.LanguageFileType;


public class AstroLanguage extends HTMLLanguage {
    public static final AstroLanguage INSTANCE = new AstroLanguage();

    private AstroLanguage() {
        super(HTMLLanguage.INSTANCE, "Astro");
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Astro";
    }

    @Override
    public boolean isCaseSensitive() {
        return true;
    }

    @Nullable
    @Override
    public LanguageFileType getAssociatedFileType() {
        return AstroFileType.INSTANCE;
    }
}
