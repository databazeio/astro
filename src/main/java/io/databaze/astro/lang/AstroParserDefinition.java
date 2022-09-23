package io.databaze.astro.lang;

import org.jetbrains.annotations.NotNull;

import com.intellij.html.embedding.HtmlCustomEmbeddedContentTokenType;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lang.xml.XMLParserDefinition;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.html.HtmlEmbeddedContentImpl;
import com.intellij.psi.impl.source.html.HtmlFileImpl;
import com.intellij.psi.impl.source.xml.stub.XmlStubBasedElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.psi.xml.XmlTokenType;

import io.databaze.astro.lang.psi.AstroElementType;

public class AstroParserDefinition implements ParserDefinition {

    @Override
    public @NotNull Lexer createLexer(final Project project) {
        return new AstroLexer();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return AstroElementType.ASTRO_FILE;
    }

    @Override
    @NotNull
    public TokenSet getWhitespaceTokens() {
        return XmlTokenType.WHITESPACES;
    }

    @Override
    @NotNull
    public TokenSet getCommentTokens() {
        return XmlTokenType.COMMENTS;
    }

    @Override
    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    @NotNull
    public PsiParser createParser(final Project project) {
        return new AstroParser();
    }

    @Override
    @NotNull
    public PsiElement createElement(ASTNode node) {
        if (node.getElementType() instanceof XmlStubBasedElementType) {
            //noinspection rawtypes
            return ((XmlStubBasedElementType) node.getElementType()).createPsi(node);
        }
        if (node.getElementType() instanceof HtmlCustomEmbeddedContentTokenType) {
            return ((HtmlCustomEmbeddedContentTokenType) node.getElementType()).createPsi(node);
        }
        if (node.getElementType() == XmlElementType.HTML_EMBEDDED_CONTENT) {
            return new HtmlEmbeddedContentImpl(node);
        }
        return PsiUtilCore.NULL_PSI_ELEMENT;
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new AstroFile(viewProvider);
    }

    @Override
    public @NotNull SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return XMLParserDefinition.canStickTokensTogether(left, right);
    }

}
