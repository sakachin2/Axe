/***************************************************************************
                               mainwindow.cpp
                             -------------------
    begin                : Mo 16.03.2009
    copyright            : (C) 2009 by Andre Simon
    email                : andre.simon1@gmx.de
 ***************************************************************************/

/*
This file is part of Highlight.

Highlight is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Highlight is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Highlight.  If not, see <http://www.gnu.org/licenses/>.
*/

#include <memory>
#include <algorithm>

#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "version.h"
#include "showtextfile.h"
#include "io_report.h"


MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent), ui(new Ui::MainWindowClass)
{
    ui->setupUi(this);
    this->setWindowTitle(QString("Highlight %1").arg( HIGHLIGHT_VERSION));

    // Read file open filter
#ifdef DATA_DIR
     QFile filterDef(QString(DATA_DIR) + "/gui_files/ext/fileopenfilter.conf");
#else
     QFile filterDef(QDir::currentPath()+"/gui_files/ext/fileopenfilter.conf");
#endif

     QRegExp rx("(\\S+)\\s?\\(\\*\\.([\\w\\d]+)");

     if (filterDef.open(QIODevice::ReadOnly | QIODevice::Text)){
           QTextStream in(&filterDef);
           QString line;

           QStringList syntaxPair;
           while (!in.atEnd()) {
               line = in.readLine();
               fileOpenFilter+=line;
               fileOpenFilter+=";;";
               if( rx.indexIn(line)!=-1){
                 syntaxPair = rx.capturedTexts();
                 ui->comboSelectSyntax->addItem(syntaxPair[1], syntaxPair[2]);
               }
            }
     } else {
         fileOpenFilter="All files (*)";
    }
     // fill themes combo
#ifdef DATA_DIR
     QDir themesDir(QString(DATA_DIR) + "/themes");
#else
     QDir themesDir(QDir::currentPath()+"/themes");
#endif
    QStringList themes = themesDir.entryList(QStringList("*.style"), QDir::Files, QDir::Name);
    for (QStringList::const_iterator constIterator = themes.constBegin();
          constIterator != themes.constEnd(); ++constIterator) {
             ui->comboTheme->addItem(QString(*constIterator).section('.',0, 0));
    }

    // load syntax mappings
   if (!loadFileTypeConfig(&extensions, &shebangs)){
     QMessageBox::warning(this, tr("Initialization error"),
                          tr("Could not find syntax definitions. Check installation."));
   }

    QObject::connect(ui->pbOpenFiles, SIGNAL(clicked()), this, SLOT(openFiles()));
    QObject::connect(ui->action_Open_files, SIGNAL(triggered()), this, SLOT(openFiles()));

    QObject::connect(ui->cbWrite2Src, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbIncLineNo, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbWrapping, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbEncoding, SIGNAL(clicked()), this, SLOT(plausibility()));

    QObject::connect(ui->comboFormat, SIGNAL(currentIndexChanged(int)), this, SLOT(plausibility()));
    QObject::connect(ui->cbReformat, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbKwCase, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbHTMLEmbedStyle, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbHTMLAnchors, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbHTMLEmbedStyle, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbHTMLInlineCSS, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbHTMLCtags, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbLATEXEmbedStyle, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbTEXEmbedStyle, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbSVGEmbedStyle, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->cbFragment, SIGNAL(clicked()), this, SLOT(plausibility()));
    QObject::connect(ui->tabIOSelection, SIGNAL(currentChanged(int)), this, SLOT(plausibility()));

    QObject::connect(ui->lvInputFiles, SIGNAL(itemSelectionChanged()), this, SLOT(updatePreview()));
    QObject::connect(ui->cbIncLineNo, SIGNAL(clicked()), this, SLOT(updatePreview()));
    QObject::connect(ui->cbKwCase, SIGNAL(clicked()), this, SLOT(updatePreview()));
    QObject::connect(ui->cbPadZeroes, SIGNAL(clicked()), this, SLOT(updatePreview()));
    QObject::connect(ui->cbReformat, SIGNAL(clicked()), this, SLOT(updatePreview()));
    QObject::connect(ui->cbWrapping, SIGNAL(clicked()), this, SLOT(updatePreview()));
    QObject::connect(ui->cbAdvWrapping, SIGNAL(clicked()), this, SLOT(updatePreview()));
    QObject::connect(ui->cbValidateInput, SIGNAL(clicked()), this, SLOT(updatePreview()));
    QObject::connect(ui->cbEncoding, SIGNAL(clicked()), this, SLOT(updatePreview()));
    QObject::connect(ui->comboEncoding, SIGNAL(currentIndexChanged(int)), this, SLOT(updatePreview()));

    QObject::connect(ui->comboFontName, SIGNAL(currentIndexChanged(int)), this, SLOT(updatePreview()));
    QObject::connect(ui->comboKwCase, SIGNAL(currentIndexChanged(int)), this, SLOT(updatePreview()));
    QObject::connect(ui->comboReformat, SIGNAL(currentIndexChanged(int)), this, SLOT(updatePreview()));
    QObject::connect(ui->comboTheme, SIGNAL(currentIndexChanged(int)), this, SLOT(updatePreview()));
    QObject::connect(ui->comboSelectSyntax, SIGNAL(currentIndexChanged(int)), this, SLOT(updatePreview()));

    QObject::connect(ui->sbLineNoWidth, SIGNAL(valueChanged(int)), this, SLOT(updatePreview()));
    QObject::connect(ui->leFontSize, SIGNAL(textChanged(QString)), this, SLOT(updatePreview()));

    setAcceptDrops(true);

    readSettings();
    plausibility();
}

MainWindow::~MainWindow()
{
    writeSettings();
    delete ui;
}

void MainWindow::openFiles(){
     QFileDialog dialog(this, "Select one or more files to open",
                         "",
                         fileOpenFilter);
     dialog.setFileMode(QFileDialog::ExistingFiles);
     dialog.setViewMode(QFileDialog::Detail);

     if (dialog.exec()) {
            addInputFiles(dialog.selectedFiles());
    }
}

void MainWindow::selectSingleFile(QLineEdit* edit, const QString& title, const QString& filter){
         QFileDialog dialog(this, title,
                         "",
                         filter);
     dialog.setFileMode(QFileDialog::ExistingFile);
     dialog.setViewMode(QFileDialog::Detail);

     if (dialog.exec()) {
            edit->setText(dialog.selectedFiles().first());
    }
}

void MainWindow::addInputFiles(const QStringList& list){
       for (QStringList::const_iterator constIterator = list.constBegin();
          constIterator != list.constEnd(); ++constIterator) {
          if (ui->lvInputFiles->findItems ( *constIterator, Qt::MatchExactly ).empty()) {
             ui->lvInputFiles->addItem(*constIterator);
          }
       }
}

void MainWindow::on_pbClearSelection_clicked(){
    QList<QListWidgetItem *> selectedItems = ui->lvInputFiles->selectedItems();
    for (int i = 0; i < selectedItems.size(); ++i) {
        delete selectedItems.at(i);
    }
}

void MainWindow::on_pbOutputDest_clicked(){
     QFileDialog dialog(this, "Select destination directory", "");
     dialog.setFileMode(QFileDialog::Directory);
     if (dialog.exec() && !dialog.selectedFiles().empty()) {
       ui->leOutputDest->setText(dialog.selectedFiles().at(0));
     }
}

 void MainWindow::writeSettings()
 {
     QSettings settings(QSettings::IniFormat, QSettings::UserScope,
                        "andre-simon.de", "highlight-gui");

     settings.beginGroup("MainWindow");
     settings.setValue("size", size());
     settings.setValue("pos", pos());
     settings.endGroup();

     settings.beginGroup("input");
     QStringList inFiles;
     for (int i=0;i<ui->lvInputFiles->count();i++){
         inFiles<<ui->lvInputFiles->item(i)->text();
     }
     const char* name="objectName";
     settings.setValue(ui->lvInputFiles->property(name).toString(),
                       inFiles);
     settings.setValue(ui->leOutputDest->property(name).toString(),
                       ui->leOutputDest->text());
     settings.setValue(ui->cbWrite2Src->property(name).toString(),
                       ui->cbWrite2Src->isChecked());
     settings.setValue(ui->cbAdvWrapping->property(name).toString(),
                       ui->cbAdvWrapping->isChecked());
     settings.setValue(ui->cbEncoding->property(name).toString(),
                       ui->cbEncoding->isChecked());
     settings.setValue(ui->cbFragment->property(name).toString(),
                       ui->cbFragment->isChecked());
     settings.setValue(ui->cbHTMLAnchors->property(name).toString(),
                       ui->cbHTMLAnchors->isChecked());
     settings.setValue(ui->cbHTMLCtags->property(name).toString(),
                       ui->cbHTMLCtags->isChecked());
     settings.setValue(ui->cbHTMLEmbedStyle->property(name).toString(),
                       ui->cbHTMLEmbedStyle->isChecked());
     settings.setValue(ui->cbHTMLEnclosePreTags->property(name).toString(),
                       ui->cbHTMLEnclosePreTags->isChecked());
     settings.setValue(ui->cbHTMLFileNameAnchor->property(name).toString(),
                       ui->cbHTMLFileNameAnchor->isChecked());
     settings.setValue(ui->cbHTMLIndex->property(name).toString(),
                       ui->cbHTMLIndex->isChecked());
     settings.setValue(ui->cbHTMLInlineCSS->property(name).toString(),
                       ui->cbHTMLInlineCSS->isChecked());
     settings.setValue(ui->cbHTMLOrderedList->property(name).toString(),
                       ui->cbHTMLOrderedList->isChecked());
     settings.setValue(ui->cbIncLineNo->property(name).toString(),
                       ui->cbIncLineNo->isChecked());
     settings.setValue(ui->cbKwCase->property(name).toString(),
                       ui->cbKwCase->isChecked());
     settings.setValue(ui->cbLATEXBabel->property(name).toString(),
                       ui->cbLATEXBabel->isChecked());
     settings.setValue(ui->cbLATEXEscQuotes->property(name).toString(),
                       ui->cbLATEXEscQuotes->isChecked());
     settings.setValue(ui->cbLATEXPrettySymbols->property(name).toString(),
                       ui->cbLATEXPrettySymbols->isChecked());
     settings.setValue(ui->cbPadZeroes->property(name).toString(),
                       ui->cbPadZeroes->isChecked());
     settings.setValue(ui->cbReformat->property(name).toString(),
                       ui->cbReformat->isChecked());
     settings.setValue(ui->cbRTFCharStyles->property(name).toString(),
                       ui->cbRTFCharStyles->isChecked());
     settings.setValue(ui->cbWrapping->property(name).toString(),
                       ui->cbWrapping->isChecked());
    settings.setValue(ui->cbValidateInput->property(name).toString(),
                       ui->cbValidateInput->isChecked());

     settings.setValue(ui->comboFormat->property(name).toString(),
                       ui->comboFormat->currentIndex());
     settings.setValue(ui->comboKwCase->property(name).toString(),
                       ui->comboKwCase->currentIndex());
     settings.setValue(ui->comboReformat->property(name).toString(),
                       ui->comboReformat->currentIndex());
     settings.setValue(ui->comboRTFPageSize->property(name).toString(),
                       ui->comboRTFPageSize->currentIndex());
     settings.setValue(ui->comboTheme->property(name).toString(),
                       ui->comboTheme->currentIndex());
     settings.setValue(ui->comboEncoding->property(name).toString(),
                       ui->comboEncoding->currentText());
     settings.setValue(ui->comboFontName->property(name).toString(),
                       ui->comboFontName->currentText());
     settings.setValue(ui->comboSelectSyntax->property(name).toString(),
                       ui->comboSelectSyntax->currentIndex());

     settings.setValue(ui->leHTMLCtagsFile->property(name).toString(),
                       ui->leHTMLCtagsFile->text());
     settings.setValue(ui->leHTMLStyleFile->property(name).toString(),
                       ui->leHTMLStyleFile->text());
     settings.setValue(ui->leHTMLStyleIncFile->property(name).toString(),
                       ui->leHTMLStyleIncFile->text());
     settings.setValue(ui->leLATEXStyleFile->property(name).toString(),
                       ui->leLATEXStyleFile->text());
     settings.setValue(ui->leLATEXStyleIncFile->property(name).toString(),
                       ui->leLATEXStyleIncFile->text());
     settings.setValue(ui->leTEXStyleFile->property(name).toString(),
                       ui->leTEXStyleFile->text());
     settings.setValue(ui->leTEXStyleIncFile->property(name).toString(),
                       ui->leTEXStyleIncFile->text());
     settings.setValue(ui->leSVGStyleFile->property(name).toString(),
                       ui->leSVGStyleFile->text());
     settings.setValue(ui->leSVGStyleIncFile->property(name).toString(),
                       ui->leSVGStyleIncFile->text());
     settings.setValue(ui->cbSVGEmbedStyle->property(name).toString(),
                       ui->cbSVGEmbedStyle->isChecked());
     settings.setValue(ui->cbLATEXEmbedStyle->property(name).toString(),
                       ui->cbLATEXEmbedStyle->isChecked());
     settings.setValue(ui->cbTEXEmbedStyle->property(name).toString(),
                       ui->cbTEXEmbedStyle->isChecked());
     settings.setValue(ui->leSVGHeight->property(name).toString(),
                       ui->leSVGHeight->text());
     settings.setValue(ui->leSVGWidth->property(name).toString(),
                       ui->leSVGWidth->text());
     settings.setValue(ui->leFontSize->property(name).toString(),
                       ui->leFontSize->text());
     settings.setValue(ui->sbLineLength->property(name).toString(),
                       ui->sbLineLength->value());
     settings.setValue(ui->sbTabWidth->property(name).toString(),
                       ui->sbTabWidth->value());
     settings.setValue(ui->tabWidget->property(name).toString(),
                       ui->tabWidget->currentIndex());
     settings.setValue(ui->leHTMLCssPrefix->property(name).toString(),
                       ui->leHTMLCssPrefix->text());
     settings.setValue(ui->tabIOSelection->property(name).toString(),
                       ui->tabIOSelection->currentIndex());


     settings.endGroup();
 }

 void MainWindow::readSettings()
 {
     QSettings settings(QSettings::IniFormat, QSettings::UserScope,
                        "andre-simon.de", "highlight-gui");

     settings.beginGroup("MainWindow");
     resize(settings.value("size", QSize(400, 400)).toSize());
     move(settings.value("pos", QPoint(200, 200)).toPoint());
     settings.endGroup();

     settings.beginGroup("input");
     const char*  name="objectName";
     ui->lvInputFiles->addItems( settings.value(ui->lvInputFiles->property(name).toString()).toStringList() );
     ui->leOutputDest->setText(settings.value(ui->leOutputDest->property(name).toString()).toString());
     ui->cbWrite2Src->setChecked(settings.value(ui->cbWrite2Src->property(name).toString()).toBool());
     ui->cbAdvWrapping->setChecked(settings.value(ui->cbAdvWrapping->property(name).toString()).toBool());
     ui->cbEncoding->setChecked(settings.value(ui->cbEncoding->property(name).toString()).toBool());
     ui->cbFragment->setChecked(settings.value(ui->cbFragment->property(name).toString()).toBool());
     ui->cbHTMLAnchors->setChecked(settings.value(ui->cbHTMLAnchors->property(name).toString()).toBool());
     ui->cbHTMLCtags->setChecked(settings.value(ui->cbHTMLCtags->property(name).toString()).toBool());
     ui->cbHTMLEmbedStyle->setChecked(settings.value(ui->cbHTMLEmbedStyle->property(name).toString()).toBool());
     ui->cbHTMLEnclosePreTags->setChecked(settings.value(ui->cbHTMLEnclosePreTags->property(name).toString()).toBool());
     ui->cbHTMLFileNameAnchor->setChecked(settings.value(ui->cbHTMLFileNameAnchor->property(name).toString()).toBool());
     ui->cbHTMLIndex->setChecked(settings.value(ui->cbHTMLIndex->property(name).toString()).toBool());
     ui->cbHTMLInlineCSS->setChecked(settings.value(ui->cbHTMLInlineCSS->property(name).toString()).toBool());
     ui->cbHTMLOrderedList->setChecked(settings.value(ui->cbHTMLOrderedList->property(name).toString()).toBool());
     ui->cbIncLineNo->setChecked(settings.value(ui->cbIncLineNo->property(name).toString()).toBool());
     ui->cbKwCase->setChecked(settings.value(ui->cbKwCase->property(name).toString()).toBool());
     ui->cbLATEXBabel->setChecked(settings.value(ui->cbLATEXBabel->property(name).toString()).toBool());
     ui->cbLATEXEscQuotes->setChecked(settings.value(ui->cbLATEXEscQuotes->property(name).toString()).toBool());
     ui->cbLATEXPrettySymbols->setChecked(settings.value(ui->cbLATEXPrettySymbols->property(name).toString()).toBool());

     ui->cbPadZeroes->setChecked(settings.value(ui->cbPadZeroes->property(name).toString()).toBool());
     ui->cbReformat->setChecked(settings.value(ui->cbReformat->property(name).toString()).toBool());
     ui->cbRTFCharStyles->setChecked(settings.value(ui->cbRTFCharStyles->property(name).toString()).toBool());
     ui->cbWrapping->setChecked(settings.value(ui->cbWrapping->property(name).toString()).toBool());
     ui->cbValidateInput->setChecked(settings.value(ui->cbValidateInput->property(name).toString()).toBool());

     ui->comboEncoding->insertItem(0, settings.value(ui->comboEncoding->property(name).toString()).toString());
     ui->comboEncoding->setCurrentIndex(0);
     ui->comboFontName->insertItem(0, settings.value(ui->comboFontName->property(name).toString()).toString());
     ui->comboFontName->setCurrentIndex(0);
     ui->comboFormat->setCurrentIndex(settings.value(ui->comboFormat->property(name).toString()).toInt());
     ui->comboKwCase->setCurrentIndex(settings.value(ui->comboKwCase->property(name).toString()).toInt());
     ui->comboReformat->setCurrentIndex(settings.value(ui->comboReformat->property(name).toString()).toInt());
     ui->comboRTFPageSize->setCurrentIndex(settings.value(ui->comboRTFPageSize->property(name).toString()).toInt());
     ui->comboTheme->setCurrentIndex(settings.value(ui->comboTheme->property(name).toString()).toInt());
     ui->comboSelectSyntax->setCurrentIndex(settings.value(ui->comboSelectSyntax->property(name).toString()).toInt());

     ui->leHTMLCtagsFile->setText(settings.value(ui->leHTMLCtagsFile->property(name).toString()).toString());
     ui->leHTMLStyleFile->setText(settings.value(ui->leHTMLStyleFile->property(name).toString()).toString());
     ui->leHTMLStyleIncFile->setText(settings.value(ui->leHTMLStyleIncFile->property(name).toString()).toString());
     ui->leLATEXStyleFile->setText(settings.value(ui->leLATEXStyleFile->property(name).toString()).toString());
     ui->leTEXStyleFile->setText(settings.value(ui->leTEXStyleFile->property(name).toString()).toString());
     ui->leLATEXStyleIncFile->setText(settings.value(ui->leLATEXStyleIncFile->property(name).toString()).toString());
     ui->leTEXStyleIncFile->setText(settings.value(ui->leTEXStyleIncFile->property(name).toString()).toString());
     ui->leSVGHeight->setText(settings.value(ui->leSVGHeight->property(name).toString()).toString());
     ui->leSVGWidth->setText(settings.value(ui->leSVGWidth->property(name).toString()).toString());
     ui->leFontSize->setText(settings.value(ui->leFontSize->property(name).toString()).toString());
     ui->leHTMLCssPrefix->setText(settings.value(ui->leHTMLCssPrefix->property(name).toString()).toString());
     ui->sbLineLength->setValue(settings.value(ui->sbLineLength->property(name).toString()).toInt());
     ui->sbTabWidth->setValue(settings.value(ui->sbTabWidth->property(name).toString()).toInt());
     ui->cbTEXEmbedStyle->setChecked(settings.value(ui->cbTEXEmbedStyle->property(name).toString()).toBool());
     ui->cbLATEXEmbedStyle->setChecked(settings.value(ui->cbLATEXEmbedStyle->property(name).toString()).toBool());
     ui->cbSVGEmbedStyle->setChecked(settings.value(ui->cbSVGEmbedStyle->property(name).toString()).toBool());
     ui->tabWidget->setCurrentIndex(settings.value(ui->tabWidget->property(name).toString()).toInt());
     ui->tabIOSelection->setCurrentIndex(settings.value(ui->tabIOSelection->property(name).toString()).toInt());

     settings.endGroup();
 }

bool MainWindow::loadFileTypeConfig(StringMap* extMap, StringMap* shebangMap) {
  if (!extMap || !shebangMap) return false;

#ifdef CONFIG_DIR
   QString filetypesPath = QDir::toNativeSeparators(QString("%1/filetypes.conf").arg(CONFIG_DIR));
#else
    QString filetypesPath = QDir::toNativeSeparators(QString("%1/filetypes.conf").arg(QDir::currentPath()));
#endif

  ConfigurationReader config(filetypesPath.toStdString());
  if (config.found())
  {
        stringstream values;
        string paramName, paramVal;
        for (unsigned int i=0;i<config.getParameterNames().size();i++){
            paramName = config.getParameterNames()[i];
            if (paramName.find("ext") != string::npos){
                values.str(StringTools::change_case(config.getParameter(paramName)));
                paramName = StringTools::getParantheseVal(paramName);
                while (values >> paramVal) {
                                extMap->insert(make_pair( paramVal,  paramName));
                }
                values.clear();
            } else if (paramName.find("shebang") != string::npos){
                values.str(config.getParameter(paramName)) ;
                        paramName = StringTools::getParantheseVal(paramName);
                while (values >> paramVal) {
                    shebangMap->insert(make_pair( paramVal,  paramName));
                }
                values.clear();
            }
        }
        return true;
  }
  return false;
}

 string MainWindow::analyzeFile(const string& file){
    ifstream inFile(file.c_str());
    string firstLine;
    getline (inFile, firstLine);
    StringMap::iterator it;
     for (it=shebangs.begin(); it!=shebangs.end();it++){
       if (Pattern::matches(it->first, firstLine)) return it->second;
     }
    return "";
}

string MainWindow::getFileType(const string& suffix, const string &inputFile)
{
    string lcSuffix = StringTools::change_case(suffix);
    string fileType = (extensions.count(lcSuffix)) ? extensions[lcSuffix] : lcSuffix ;
    if (!fileType.empty()) return fileType;
    return analyzeFile(inputFile);
}

string MainWindow::getFileSuffix(const string& fileName)
{
  unsigned int ptPos=fileName.rfind(".");
  return (ptPos == string::npos) ? "" : fileName.substr(ptPos+1,
                                        fileName.length());
}

void MainWindow::on_action_Exit_triggered()
{
    this->close();
}

void MainWindow::on_action_About_Highlight_triggered()
{
     QMessageBox::about( this, "About Highlight",
                         QString("Highlight is a code to formatted text converter.\n\n"
                         "Highlight GUI %1\n"
                         "(C) 2002-2010 Andre Simon <andre.simon1 at gmx.de>\n\n"
                         "Artistic Style Classes\n(C) 1998-2002 Tal Davidson\n"
                         "(C) 2006-2009 Jim Pattee <jimp03 at email.com>\n\n"
                         "Regex library\n(C) 2003-2008 Jeffery Stuart <stuart at cs.unr.edu>\n\n"
                         "Built with Qt version %2\n\n"
                         "Released under the terms of the GNU GPL license.\n\n"
                         "The highlight logo is based on the image \"Alcedo Atthis\" by Lukasz Lukasik.\n"
                         "The original was published under the terms of the GNU FDL in the Wikimedia Commons database.\n\n"
                         "Updates: http://www.andre-simon.de/\n").arg(HIGHLIGHT_VERSION).arg(QString(qVersion ())) );
}

 highlight::OutputType MainWindow::getOutputType(){

    switch (ui->comboFormat->currentIndex()){
     case 0: return highlight::HTML;
     case 1: return highlight::XHTML;
     case 2: return highlight::LATEX;
     case 3: return highlight::TEX;
     case 4: return highlight::RTF;
     case 5: return highlight::SVG;
     case 6: return highlight::XML;
     case 7: return highlight::BBCODE;
    }
    return highlight::HTML;
}

 QString MainWindow::getOutFileSuffix(){
  switch (ui->comboFormat->currentIndex()) {
    case 0: return ".html";
    case 1: return ".xhtml";
    case 2:
    case 3: return ".tex";
    case 4: return ".rtf";
    case 5: return ".svg";
    case 6: return ".xml";
    case 7: return ".bbcode";
  }
  return ".html";
}


void MainWindow::applyCtrlValues(highlight::CodeGenerator* generator, bool previewMode){
    if (!generator) return;

    highlight::OutputType outType=getOutputType();

    if (!previewMode){
        QLineEdit * styleIncFile=NULL;
        QLineEdit * styleOutFile=NULL;
        QCheckBox * embedStyle=NULL;

        switch(outType) {
            case highlight::HTML:
            case highlight::XHTML:  styleIncFile = ui->leHTMLStyleIncFile;
                         styleOutFile = ui->leHTMLStyleFile;
                         embedStyle  = ui->cbHTMLEmbedStyle;
                         break;
            case highlight::LATEX:
                         styleIncFile = ui->leLATEXStyleIncFile;
                         styleOutFile = ui->leLATEXStyleFile;
                         embedStyle  = ui->cbLATEXEmbedStyle;
                         break;
            case highlight::TEX:    styleIncFile = ui->leTEXStyleIncFile;
                         styleOutFile = ui->leTEXStyleFile;
                         embedStyle  = ui->cbTEXEmbedStyle;
                         break;
            case highlight::SVG:    styleIncFile = ui->leSVGStyleIncFile;
                         styleOutFile = ui->leSVGStyleFile;
                         embedStyle  = ui->cbSVGEmbedStyle;
                         break;
            default: break;
            }
          if (styleIncFile)
              generator->setStyleInputPath(QDir::toNativeSeparators(styleIncFile->text()).toStdString());
          if (styleOutFile)
              generator->setStyleOutputPath(styleOutFile->text().toStdString());
          if (embedStyle)
               generator->setIncludeStyle(embedStyle->isChecked());

          generator->setFragmentCode(ui->cbFragment->isChecked());

          generator->setHTMLAttachAnchors(ui->cbHTMLAnchors->isChecked());
          generator->setHTMLOrderedList(ui->cbHTMLOrderedList->isChecked());
          generator->setHTMLInlineCSS(ui->cbHTMLInlineCSS->isChecked() && ui->cbHTMLInlineCSS->isEnabled());
          generator->setHTMLEnclosePreTag(ui->cbHTMLEnclosePreTags->isChecked());
          if (ui->leHTMLCssPrefix->text().size())
              generator->setHTMLClassName(ui->leHTMLCssPrefix->text().toStdString());

          generator->setLATEXReplaceQuotes(ui->cbLATEXEscQuotes->isChecked());
          generator->setLATEXNoShorthands(ui->cbLATEXBabel->isChecked());
          generator->setLATEXPrettySymbols(ui->cbLATEXPrettySymbols->isChecked());

          generator->setRTFPageSize(ui->comboRTFPageSize->currentText().toLower().toStdString());
          generator->setRTFCharStyles(ui->cbRTFCharStyles->isChecked());

          generator->setSVGSize(ui->leSVGWidth->text().toStdString(), ui->leSVGHeight->text().toStdString());
    }

    generator->setPrintLineNumbers( ui->cbIncLineNo->isChecked());
    generator->setPrintZeroes(ui->cbPadZeroes->isEnabled() && ui->cbPadZeroes->isChecked());

#ifdef DATA_DIR
      QString themePath = QString("%1/themes/%2.style").arg(
              DATA_DIR).arg(ui->comboTheme->currentText());
#else
      QString themePath = QString("%1/themes/%2.style").arg(
              QDir::currentPath()).arg(ui->comboTheme->currentText());
#endif

    generator->initTheme(themePath.toStdString());

    if (ui->cbEncoding->isChecked()) {
        generator->setEncoding(ui->comboEncoding->currentText().toStdString());
    } else {
        generator->setEncoding("none");
    }
    generator->setValidateInput(ui->cbValidateInput->isChecked());
    generator->setLineNumberWidth(ui->sbLineNoWidth->value());

    if (getOutputType()!=highlight::LATEX && getOutputType()!=highlight::TEX){
      string fntName=ui->comboFontName->currentText().toStdString();
      if(fntName.size()) generator->setBaseFont(fntName);
      string fntSize=ui->leFontSize->text().toStdString();
      if(fntSize.size()) generator->setBaseFontSize(fntSize);
    }

    int lineLength = 0;
    if (ui->cbWrapping->isChecked()){
         lineLength = (   ui->cbIncLineNo->isChecked()
                       && ui->sbLineNoWidth->value() >  ui->sbLineLength->value())?
                          ui->sbLineLength->value() - ui->sbLineNoWidth->value()
                       :  ui->sbLineLength->value();
    }
    generator->setPreformatting(getWrappingStyle(), lineLength, ui->sbTabWidth->value());

    if(ui->cbKwCase->isChecked()){
        StringTools::KeywordCase kwCase=StringTools::CASE_UNCHANGED;
        switch (ui->comboKwCase->currentIndex()){
                case 0: kwCase=StringTools::CASE_UPPER; break;
                case 1: kwCase=StringTools::CASE_LOWER; break;
                case 2: kwCase=StringTools::CASE_CAPITALIZE; break;
        }
        generator->setKeyWordCase(kwCase);
    }

   if (ui->cbReformat->isChecked()){
       generator->initIndentationScheme(ui->comboReformat->currentText().toLower().toStdString());

    }

   if (    ui->cbHTMLCtags->isChecked() && !ui->leHTMLCtagsFile->text().isEmpty()
       && (outType==highlight::HTML || outType==highlight::XHTML)){
       if (!generator->initTagInformation(ui->leHTMLCtagsFile->text().toStdString())){
         QMessageBox::warning(this, tr("Tags file error"),
                              tr("Could not read tags information in \"%1\"").arg(ui->leHTMLCtagsFile->text()));
      }
   }
}

highlight::WrapMode MainWindow::getWrappingStyle(){
  if (!ui->cbAdvWrapping->isChecked() && ui->cbAdvWrapping->isEnabled())
     return highlight::WRAP_SIMPLE;
  return (ui->cbWrapping->isChecked())?highlight::WRAP_DEFAULT:highlight::WRAP_DISABLED;
}


void MainWindow::on_pbStartConversion_clicked(){

    if (!ui->lvInputFiles->count()) return;

    if (!ui->cbWrite2Src->isChecked() && !QDir(ui->leOutputDest->text()).exists()){
        QMessageBox::warning(this, tr("Output error"), tr("Output directory does not exist!"));
        ui->leOutputDest->setFocus();
        return;
    }
    highlight::OutputType outType = getOutputType();
    QCheckBox* cbEmbed=NULL;
    QLineEdit* leStyleFile=NULL;

    if ( outType==highlight::HTML || outType==highlight::XHTML) {
        cbEmbed = ui->cbHTMLEmbedStyle;
        leStyleFile = ui->leHTMLStyleFile;
    }
    else if ( outType==highlight::LATEX ) {
         cbEmbed = ui->cbLATEXEmbedStyle;
        leStyleFile = ui->leLATEXStyleFile;
    }
    else if (outType==highlight::TEX ) {
        cbEmbed = ui->cbTEXEmbedStyle;
        leStyleFile = ui->leTEXStyleFile;
    }    else if (outType==highlight::SVG ) {
        cbEmbed = ui->cbSVGEmbedStyle;
        leStyleFile = ui->leSVGStyleFile;
    }

    if (cbEmbed && leStyleFile ) {
       if ( !cbEmbed->isChecked() && leStyleFile->text().isEmpty()){
         ui->tabWidget->setCurrentIndex(1);
         if ( outType==highlight::HTML || outType==highlight::XHTML){
                 ui->tabWidget->setCurrentIndex(0);
          }
         leStyleFile->setFocus();
         QMessageBox::warning(this, tr("Output error"), tr("You must define a style output file!"));
         return;
       }
    }
    ui->pbStartConversion->setDisabled(true);
    ui->pbCopyFile2CP->setDisabled(true);
    this->setCursor(Qt::WaitCursor);

    QTime t;
    t.start();

    auto_ptr<highlight::CodeGenerator> generator(highlight::CodeGenerator::getInstance(outType));

    applyCtrlValues(generator.get(), false);
    string currentFile, suffix;
    string outfileName;
    string outfileSuffix = getOutFileSuffix().toStdString();
    QString langDefPath;
    QString inFileName, inFilePath;
    highlight::ParseError error;
    highlight::LoadResult loadRes;
    QStringList inputErrors, outputErrors, reformatErrors;

    for (int i=0; i<ui->lvInputFiles->count(); i++){
     inFilePath =  ui->lvInputFiles->item(i)->text();
     currentFile = inFilePath.toStdString();
     suffix = getFileType(getFileSuffix(currentFile), currentFile);
#ifdef DATA_DIR
    langDefPath = QDir::toNativeSeparators(QString("%1/langDefs/%2.lang").arg(DATA_DIR).arg(QString::fromStdString(suffix)));
#else
     langDefPath = QDir::toNativeSeparators(QString("%1/langDefs/%2.lang").arg(QDir::currentPath()).arg(QString::fromStdString(suffix)));
#endif

     loadRes=generator->initLanguage(langDefPath.toStdString());
     if (loadRes==highlight::LOAD_FAILED_REGEX){
             QMessageBox::warning(this, tr("Language definition error"),
                         tr("Invalid regular expression in %1:\n%2").arg(langDefPath).arg(
                         QString::fromStdString( generator->getLanguage().getFailedRegex())));
             break;
        } else  if (loadRes==highlight::LOAD_FAILED) {
           QMessageBox::warning(this, tr("Unknown syntax"), tr("Could not convert %1").arg(QString::fromStdString(currentFile)));
           inputErrors.append(inFilePath);
       } else {

          if (ui->cbReformat->isChecked()&& !generator->formattingIsPossible()){
             reformatErrors.append(inFilePath);
          }

          if (ui->cbWrite2Src->isChecked()) {
            outfileName = currentFile;
          } else {
              QFileInfo outFileInfo;
              outFileInfo.setFile(ui->leOutputDest->text(), QFileInfo(inFilePath).fileName());
              outfileName = outFileInfo.absoluteFilePath().toStdString();
          }
          outfileName += outfileSuffix;

          inFileName = QFileInfo(inFilePath).fileName();
          if (ui->cbHTMLFileNameAnchor->isChecked()){
            generator->setHTMLAnchorPrefix(inFileName.toStdString());
          }
          generator->setTitle(inFileName.toStdString());
          error = generator->generateFile(currentFile,
                                         outfileName );
          if (error != highlight::PARSE_OK){
            if (error == highlight::BAD_INPUT) {
              inputErrors.append(inFilePath);
            }
            else {
              outputErrors.append(inFilePath);
            }
          }
          ui->progressBar->setValue(100*i / ui->lvInputFiles->count());
        }
   }

   // write external Stylesheet
   if (cbEmbed && leStyleFile && !cbEmbed->isChecked()) {
       QString stylePath=QFileInfo(ui->leOutputDest->text(),   leStyleFile->text()).absoluteFilePath();
       bool styleFileOK=generator -> printExternalStyle(QDir::toNativeSeparators(stylePath).toStdString());
       if (!styleFileOK) {
           outputErrors.append(stylePath);
       }
   }

   // write HTML index file
   if (    (outType==highlight::HTML || outType==highlight::XHTML)
        && ui->cbHTMLIndex->isChecked() && !ui->cbWrite2Src->isChecked()) {
      vector <string>  fileList;
      for (int i=0;i<ui->lvInputFiles->count();i++){
         fileList.push_back(QDir::toNativeSeparators(ui->lvInputFiles->item(i)->text()).toStdString());
      }
      QString outPath = QDir::toNativeSeparators(ui->leOutputDest->text());
      if (!outPath.endsWith(QDir::separator())) outPath.append(QDir::separator());
      bool indexFileOK=generator->printIndexFile(fileList,  outPath.toStdString());
      if (!indexFileOK) {
           outputErrors.append(outPath+ ((outType==highlight::HTML)?"index.html":"index.xhtml"));
      }
   }

   statusBar()->showMessage(tr("Converted %1 files in %2 ms").arg(ui->lvInputFiles->count()).arg(t.elapsed()));
   ui->progressBar->reset();
   this->setCursor(Qt::ArrowCursor);
   ui->pbStartConversion->setEnabled(true);
   ui->pbCopyFile2CP->setEnabled(true);

   if (!inputErrors.isEmpty() || !outputErrors.isEmpty() || !reformatErrors.isEmpty()){
       io_report report;
       report.addInputErrors(inputErrors);
       report.addOutputErrors(outputErrors);
       report.addReformatErrors(reformatErrors);
       report.exec();
       if (report.removeInputErrorFiles()){
           QList<QListWidgetItem*> items;
           for (int i=0;i<inputErrors.count();i++){
               items=ui->lvInputFiles->findItems(inputErrors.at(i),Qt::MatchExactly);
               for (int j=0;j<items.count();j++){
                   delete items.at(j);
               }
           }
       }
   }
}

void MainWindow::on_pbCopyFile2CP_clicked(){

    highlight2Clipboard(false);
}

void MainWindow::highlight2Clipboard(bool getDataFromCP){

    if ((!getDataFromCP &&NULL==ui->lvInputFiles->currentItem())
        || (getDataFromCP && savedClipboardContent.isEmpty())) return;

     this->setCursor(Qt::WaitCursor);

     auto_ptr<highlight::CodeGenerator> generator(
             highlight::CodeGenerator::getInstance(getOutputType()));

     applyCtrlValues(generator.get(), false);

     string suffix;
     if (getDataFromCP){
         suffix= getFileType((ui->comboSelectSyntax->itemData(ui->comboSelectSyntax->currentIndex())).toString().toStdString(),"");
     }else {
         string currentFile = ui->lvInputFiles->currentItem()->text().toStdString();
         suffix = getFileType(getFileSuffix(currentFile), currentFile);

         QString inFileName = QFileInfo(ui->lvInputFiles->currentItem()->text()).fileName();
         generator->setTitle(inFileName.toStdString());
     }

#ifdef DATA_DIR
 QString langPath = QDir::toNativeSeparators(QString("%1/langDefs/%2.lang").arg(
         DATA_DIR).arg(QString::fromStdString(suffix)));
#else
     QString langPath = QDir::toNativeSeparators(QString("%1/langDefs/%2.lang").arg(
             QDir::currentPath()).arg(QString::fromStdString(suffix)));
#endif

     if ( generator->initLanguage(langPath.toStdString()) != highlight::LOAD_FAILED){
          QString clipBoardData;
          if (getDataFromCP){
               clipBoardData= QString::fromStdString( generator->generateString(savedClipboardContent.toStdString()));
           } else {
                clipBoardData= QString::fromStdString( generator->generateStringFromFile(ui->lvInputFiles->currentItem()->text().toStdString()));
          }

          QClipboard *clipboard = QApplication::clipboard();
          if (clipboard) {
              highlight::OutputType outputType = getOutputType();
              if ( outputType==highlight::RTF){
                   QMimeData *mimeData = new QMimeData();
#ifdef WIN32
                  mimeData->setData("Rich Text Format", clipBoardData.toAscii());
#else
                  mimeData->setData("text/rtf", clipBoardData.toAscii());
#endif
                  clipboard->setMimeData(mimeData);
              } else {
                   clipboard->setText(clipBoardData);
              }
          }
     } else {
           statusBar()->showMessage(
                   tr("Conversion of \"%1\" not possible.").arg((getDataFromCP)?tr("clipboard data"):ui->lvInputFiles->currentItem()->text()));
     }
    this->setCursor(Qt::ArrowCursor);
}

 void MainWindow::plausibility(){
     ui->leOutputDest->setEnabled(!ui->cbWrite2Src->isChecked());
     ui->pbOutputDest->setEnabled(!ui->cbWrite2Src->isChecked());
     ui->pbOutputDest->setEnabled(!ui->cbWrite2Src->isChecked());
     ui->leOutputDest->setEnabled(!ui->cbWrite2Src->isChecked());
     ui->cbPadZeroes->setEnabled(ui->cbIncLineNo->isChecked());
     ui->cbAdvWrapping->setEnabled(ui->cbWrapping->isChecked());
     ui->comboEncoding->setEnabled(ui->cbEncoding->isChecked());

     ui->comboReformat->setEnabled(ui->cbReformat->isChecked());
     ui->comboKwCase->setEnabled(ui->cbKwCase->isChecked());
     ui->cbHTMLInlineCSS->setEnabled(ui->cbHTMLEmbedStyle->isChecked());
     ui->cbHTMLFileNameAnchor->setEnabled(ui->cbHTMLAnchors->isChecked());
     ui->leHTMLStyleFile->setEnabled(!ui->cbHTMLEmbedStyle->isChecked());
     ui->leHTMLStyleIncFile->setEnabled(ui->cbHTMLEmbedStyle->isChecked() && !ui->cbHTMLInlineCSS->isChecked());
     ui->pbHTMLChooseStyleIncFile->setEnabled(ui->cbHTMLEmbedStyle->isChecked() &&!ui->cbHTMLInlineCSS->isChecked());
     ui->leHTMLCssPrefix->setEnabled(!ui->cbHTMLInlineCSS->isChecked());
     ui->leHTMLCtagsFile->setEnabled(ui->cbHTMLCtags->isChecked());
     ui->pbHTMLChooseTagsFile->setEnabled(ui->cbHTMLCtags->isChecked());
     ui->leLATEXStyleFile->setEnabled(!ui->cbLATEXEmbedStyle->isChecked());
     ui->leTEXStyleFile->setEnabled(!ui->cbTEXEmbedStyle->isChecked());
     ui->leSVGStyleFile->setEnabled(!ui->cbSVGEmbedStyle->isChecked());
     ui->cbFragment->setEnabled( getOutputType()!=highlight::RTF && getOutputType()!=highlight::SVG );
     ui->sbLineNoWidth->setEnabled(ui->cbIncLineNo->isChecked());
     ui->cbHTMLIndex->setEnabled(!ui->cbWrite2Src->isChecked());
     ui->cbHTMLEnclosePreTags->setEnabled(ui->cbFragment->isChecked());
     ui->cbHTMLAnchors->setEnabled(ui->cbIncLineNo->isChecked());
     ui->cbHTMLFileNameAnchor->setEnabled(ui->cbIncLineNo->isChecked());
     ui->cbHTMLOrderedList->setEnabled(ui->cbIncLineNo->isChecked());

      ui->pbCopyToCP->setEnabled(!savedClipboardContent.isEmpty());

     switch (ui->comboFormat->currentIndex()) {
    case 0:
    case 1:
         ui->stackedSpecificOptions->setCurrentIndex(0);
         break;
    case 2:
         ui->stackedSpecificOptions->setCurrentIndex(1);
         break;
    case 3:
         ui->stackedSpecificOptions->setCurrentIndex(2);
         break;
    case 4:
         ui->stackedSpecificOptions->setCurrentIndex(3);
         break;
    case 5:
         ui->stackedSpecificOptions->setCurrentIndex(4);
         break;
    default:
         ui->stackedSpecificOptions->setCurrentIndex(5);
         break;
  }
 }

 void MainWindow::updatePreview() {

     // is clipboard tab chosen?
     bool getDataFromCP = ui->tabIOSelection->currentIndex()==1;

     if ((!getDataFromCP && NULL==ui->lvInputFiles->currentItem())
         || (getDataFromCP && savedClipboardContent.isEmpty())) return;

     int vScroll = ui->browserPreview->verticalScrollBar()->value();
     int hScroll = ui->browserPreview->horizontalScrollBar()->value();
     this->setCursor(Qt::WaitCursor);
     highlight::HtmlGenerator pwgenerator;
     pwgenerator.setIncludeStyle(true);
     pwgenerator.setHTMLInlineCSS(true);
     if (!getDataFromCP){
         pwgenerator.setMaxInputLineCnt(500);
     }

     applyCtrlValues(&pwgenerator, true);

     string suffix;

     if (getDataFromCP){
          suffix= getFileType((ui->comboSelectSyntax->itemData(ui->comboSelectSyntax->currentIndex())).toString().toStdString(),"");
     } else {
         string currentFile = ui->lvInputFiles->currentItem()->text().toStdString();
         suffix = getFileType(getFileSuffix(currentFile), currentFile);
     }
#ifdef DATA_DIR
 QString langPath = QDir::toNativeSeparators(QString("%1/langDefs/%2.lang").arg(
         DATA_DIR).arg(QString::fromStdString(suffix)));
#else
     QString langPath = QDir::toNativeSeparators(QString("%1/langDefs/%2.lang").arg(
             QDir::currentPath()).arg(QString::fromStdString(suffix)));
#endif


     if ( pwgenerator.initLanguage(langPath.toStdString()) != highlight::LOAD_FAILED){

          ui->lbPreview->setText(tr("Preview (%1):").arg(
                  (getDataFromCP)?tr("clipboard data"):ui->lvInputFiles->currentItem()->text()) );

          statusBar()->showMessage(tr("Current syntax: %1").arg(   QString::fromStdString(pwgenerator.getLanguage().getDescription())));
          QString previewData;

          // fix utf-8 data preview - to be improved (other encodings??)
          if (ui->cbEncoding->isChecked() && ui->comboEncoding->currentText().toLower()=="utf-8"){
              if (getDataFromCP){
                   previewData= QString::fromUtf8( pwgenerator.generateString(savedClipboardContent.toStdString()).c_str());
              } else {
                    previewData= QString::fromUtf8( pwgenerator.generateStringFromFile(ui->lvInputFiles->currentItem()->text().toStdString()).c_str());
              }
          } 
          else {
              if (getDataFromCP){
                   previewData= QString::fromStdString( pwgenerator.generateString(savedClipboardContent.toStdString()));
              } else {
                    previewData= QString::fromStdString( pwgenerator.generateStringFromFile(ui->lvInputFiles->currentItem()->text().toStdString()));
              }
          }

          ui->browserPreview->setHtml(previewData);
     } else {
         statusBar()->showMessage(tr("Preview of \"%1\" not possible.").arg((getDataFromCP)?tr("clipboard data"):ui->lvInputFiles->currentItem()->text()));
     }
     ui->browserPreview->verticalScrollBar()->setValue(vScroll);
     ui->browserPreview->horizontalScrollBar()->setValue(hScroll);
     this->setCursor(Qt::ArrowCursor);
 }



void MainWindow::on_pbHTMLChooseTagsFile_clicked()
{
    selectSingleFile(ui->leHTMLCtagsFile, tr("Choose a ctags file"), "*");
}

void MainWindow::on_action_Manual_triggered()
{
    ShowTextFile show;
    show.setFileName("README");
    show.exec();
}

void MainWindow::on_action_Changelog_triggered()
{
    ShowTextFile show;
    show.setFileName("ChangeLog");
    show.exec();
}

void MainWindow::on_action_License_triggered()
{
    ShowTextFile show;
    show.setFileName("COPYING");
    show.exec();
}

void MainWindow::on_pbHTMLChooseStyleIncFile_clicked()
{
        selectSingleFile(ui->leHTMLStyleIncFile, tr("Choose a style include file"), "*.css");
}
void MainWindow::on_pbSVGChooseStyleIncFile_clicked()
{
        selectSingleFile(ui->leSVGStyleIncFile, tr("Choose a style include file"), "*.css");
}
void MainWindow::on_pbLATEXChooseStyleIncFile_clicked()
{
    selectSingleFile(ui->leLATEXStyleIncFile, tr("Choose a style include file"), "*.sty");
}

void MainWindow::on_pbTEXChooseStyleIncFile_clicked()
{
        selectSingleFile(ui->leTEXStyleIncFile, tr("Choose a style include file"), "*.sty");
}


void MainWindow::on_actionAbout_translations_triggered()
{
    QMessageBox::information(this, tr("About providing translations"),
                             tr("The GUI was developed using the Qt toolkit, and "
                                "translations may be provided using the tools Qt Linguist and lrelease.\n"
                                "The highlight.ts file for Linguist resides in the src/gui-qt subdirectory.\n"
                                "The qm file generated by lrelease has to be saved in gui-files/l10n.\n\n"
                                "Please send a note to as (at) andre-simon (dot) de if you have issues during translating "
                                "or if you have finished or updated a translation."));
}

 void MainWindow::dropEvent(QDropEvent *event)
 {
     const QMimeData *mimeData = event->mimeData();

     if (mimeData && mimeData->hasUrls()) {
         QList<QUrl> urlList = mimeData->urls();
         QString url;
         for (int i = 0; i < urlList.size(); ++i) {
             url=urlList.at(i).toLocalFile();
             if (ui->lvInputFiles->findItems ( url, Qt::MatchExactly ).empty()) {
               ui->lvInputFiles->addItem(url);
             }
         }
     }
     event->acceptProposedAction();
 }

 void MainWindow::dragEnterEvent(QDragEnterEvent *event)
 {
     event->acceptProposedAction();
 }

 void MainWindow::dragMoveEvent(QDragMoveEvent *event)
 {
     event->acceptProposedAction();
 }

  void MainWindow::dragLeaveEvent(QDragLeaveEvent *event)
 {
     event->accept();
 }

void MainWindow::on_pbPasteFromCB_clicked()
{
    QClipboard *clipboard = QApplication::clipboard();
    if (clipboard) {
        savedClipboardContent = clipboard->text();
        updatePreview();
        ui->pbCopyToCP->setEnabled(!savedClipboardContent.isEmpty());
   }

}

void MainWindow::on_pbCopyToCP_clicked()
{
    highlight2Clipboard(true);
}
