/***************************************************************************
                               mainwindow.h
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

#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QtGui/QMainWindow>
#include <QtGui/QMessageBox>
#include <QtGui/QFileDialog>
#include <QSettings>
#include <QDir>
#include <QClipboard>
#include <QMimeData>
#include <QTime>
#include <QLineEdit>
#include <QString>
#include <QTextStream>
#include <QScrollBar>
#include <QDropEvent>

#include "version.h"
#include "codegenerator.h"
#include "htmlgenerator.h"
#include "configurationreader.h"

#include "enums.h"

typedef map<string, string> StringMap;

namespace Ui
{
    class MainWindowClass;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = 0);
    ~MainWindow();

    void addInputFiles(const QStringList& list);

private:
    Ui::MainWindowClass *ui;
    StringMap extensions,  shebangs;
    QString fileOpenFilter;
    QString savedClipboardContent;

    void readSettings();
    void writeSettings();
    highlight::OutputType getOutputType();
    highlight::WrapMode getWrappingStyle();
    QString getOutFileSuffix();
    void applyCtrlValues(highlight::CodeGenerator* generator, bool previewMode);
    void selectSingleFile(QLineEdit*, const QString&, const QString&);
    bool loadFileTypeConfig(StringMap* extMap, StringMap* shebangMap);
    void highlight2Clipboard(bool getDataFromCP);

    string analyzeFile(const string& file);
    string getFileType(const string& suffix, const string &inputFile);
    string getFileSuffix(const string& fileName);

     void dragEnterEvent(QDragEnterEvent *event);
     void dragMoveEvent(QDragMoveEvent *event);
     void dragLeaveEvent(QDragLeaveEvent *event);
     void dropEvent(QDropEvent *event);

public slots:
    //This is a slot like the ones we used in our last tutorial
    // The difference here that it gets automatically connect
    // If you use on_objectname_signalname it's like connect(pushButton,SIGNAL(clicked()),this,SLOT(on_pushButton_clicked()))

    void on_pbClearSelection_clicked();
    void on_pbOutputDest_clicked();
    void on_pbStartConversion_clicked();
    void on_pbCopyFile2CP_clicked();

private slots:

    void on_pbCopyToCP_clicked();
    void on_pbPasteFromCB_clicked();
    void on_actionAbout_translations_triggered();
    void on_pbTEXChooseStyleIncFile_clicked();
    void on_pbLATEXChooseStyleIncFile_clicked();
    void on_pbHTMLChooseStyleIncFile_clicked();
    void on_pbSVGChooseStyleIncFile_clicked();
    void on_action_License_triggered();
    void on_action_Changelog_triggered();
    void on_action_Manual_triggered();
    void on_pbHTMLChooseTagsFile_clicked();
    void on_action_About_Highlight_triggered();
    void on_action_Exit_triggered();
    void plausibility();
    void updatePreview();
    void openFiles();

};

#endif // MAINWINDOW_H