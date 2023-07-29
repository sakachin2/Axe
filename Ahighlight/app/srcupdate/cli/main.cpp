/***************************************************************************
                          main.cpp  -  description
                             -------------------
    begin                : Die Apr 23 22:16:35 CEST 2002
    copyright            : (C) 2002-2009 by Andre Simon
    email                : andre.simon1@gmx.de

   Highlight is a universal source code to HTML converter. Syntax highlighting
   is formatted by Cascading Style Sheets. It's possible to easily enhance
   highlight's parsing database.

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
//#include "main.h"                                                //+3623R~
#include <main.h>           //     2023/06/23                      //+3623I~
#include "re/Pattern.h"

#define MAX_LINE__WIDTH       80

using namespace std;

void HLCmdLineApp::printVersionInfo()
{
	cout << "\n highlight version "
	<< HIGHLIGHT_VERSION
	<< "\n Copyright (C) 2002-2010 Andre Simon <andre.simon1 at gmx.de>"
        << "\n\n Artistic Style Classes (1.24)"
	<< "\n Copyright (C) 2006-2010 by Jim Pattee <jimp03 at email.com>"
	<< "\n Copyright (C) 1998-2002 by Tal Davidson"
	<< "\n\n Regex library (1.09.00)"
	<< "\n Copyright (C) 2003-2008 Jeffery Stuart <stuart at cs.unr.edu>"
	<< "\n\n xterm 256 color matching functions"
	<< "\n Copyright (C) 2006 Wolfgang Frisch <wf at frexx.de>"
	<< "\n\n Argparser class"
	<< "\n Copyright (C) 2006-2008 Antonio Diaz Diaz <ant_diaz at teleline.es>"
	<< "\n\n This software is released under the terms of the GNU General "
	<< "Public License."
	<< "\n For more information about these matters, see the file named "
	<< "COPYING.\n\n";
}

void HLCmdLineApp::printBadInstallationInfo()
{
	cerr << "highlight: Data directory not found ("<<DataDir::LSB_DATA_DIR<<")."
	" Bad installation or wrong "<< OPT_DATADIR << " parameter."
	<< "\n\nCopy the highlight files into one of the directories listed "
	<< "in INSTALL.\nYou may also set the data directory with "
	<< OPT_DATADIR << " and " << OPT_ADDDATADIR << ".\n";
}

bool HLCmdLineApp::printInstalledThemes()
{
	vector <string> filePaths;
	string wildcard="*.style";
	string directory= dataDir.getThemePath();
	string searchDir = directory + wildcard;

	bool directoryOK = Platform::getDirectoryEntries ( filePaths, searchDir, true );
	if ( !directoryOK )
	{
		cerr << "highlight: Could not access directory "
		<<  searchDir
		<< ", aborted.\n";
		return false;
	}

	cout << "\nInstalled themes"
	<< " (located in " << directory << "):\n\n";

	sort ( filePaths.begin(), filePaths.end() );
	string temp;

	for ( unsigned int i=0;i< filePaths.size(); i++ )
	{
		temp = ( filePaths[i] ).substr ( directory.length() );
		cout <<temp.substr ( 1, temp.length()- wildcard.length() ) << endl;
	}
	cout <<"\nUse name of the desired theme"
	<< " with the --" OPT_STYLE " option.\n" << endl;
	return true;
}


bool HLCmdLineApp::printInstalledLanguages()
{
	vector <string> filePaths;
	string wildcard="*.lang";
	string directory=dataDir.getLangPath();
	string searchDir = directory + wildcard;

	bool directoryOK = Platform::getDirectoryEntries ( filePaths, searchDir, true );
	if ( !directoryOK )
	{
		cerr << "highlight: Could not access directory "
		<<  searchDir
		<< ", aborted.\n";
		return false;
	}

	sort ( filePaths.begin(), filePaths.end() );
	string suffix, desc;
	cout << "\nInstalled language definitions"
	<< " (located in " << directory << "):\n\n";

	for ( unsigned int i=0;i< filePaths.size(); i++ )
	{
		ConfigurationReader lang ( filePaths[i] );
		desc = lang.getParameter ( "description" );
		suffix = ( filePaths[i] ).substr ( directory.length() ) ;
		suffix = suffix.substr ( 1, suffix.length()- wildcard.length() );
		cout << setw ( 20 ) <<setiosflags ( ios::left ) <<desc<<": "<<suffix;
		int extCnt=0;
		for (StringMap::iterator it=extensions.begin();it!=extensions.end();it++) {
			if (it->second==suffix ) {
				
				cout << ((++extCnt==1)?" ( ":" ")<<it->first; 
			}
		}
		cout << ((extCnt)?" )":"")<<endl;
	}
	cout <<"\nUse name of the desired language"
	<< " with the --" OPT_SYNTAX " option.\n" << endl;
	return true;
}

void HLCmdLineApp::printDebugInfo ( const highlight::LanguageDefinition &lang,
                                    const string & langDefPath )
{
	cerr << "\nLoading language definition:\n" << langDefPath;
	cerr << "\n\nDescription: " << lang.getDescription();
	cerr << "\n\nSYMBOLS (followed by states):\n" << lang.getSymbolString();
	cerr << "\n\nREGEX:\n";
	highlight::RegexElement *re=NULL;
	for ( unsigned int i=0; i<lang.getRegexElements().size(); i++ )
	{
		re = lang.getRegexElements() [i];
		cerr << "State "<<re->open<<":\t"<<re->rePattern->getPattern() <<"\n";
	}
	cerr << "\nKEYWORDS:\n";
	highlight::KeywordMap::iterator it;
	highlight::KeywordMap keys=lang.getKeywords();
	for ( it=keys.begin(); it!=keys.end(); it++ )
	{
		cerr << " "<< it->first << "("<< it->second << ")";
	}
	cerr <<"\n\n";
}

void HLCmdLineApp::printConfigInfo ( const string& configFile )
{
	cout << "\nRoot paths (modify with --" OPT_DATADIR  " and --" OPT_ADDDATADIR "):\n";
	cout << "  Data directory:         "<<dataDir.getDir() <<"\n";
	if ( !dataDir.getAdditionalDataDir().empty() )
		cout << "  User defined directory: "<<dataDir.getAdditionalDataDir() <<"\n";
	cout << "\nDefault search paths:\n";
	cout << "  Language definitions:   "<<dataDir.getLangPath ( "", true ) <<"\n";
	cout << "  Colour themes:          "<<dataDir.getThemePath ( "", true ) <<"\n";

	if ( !dataDir.getAdditionalDataDir().empty() )
	{
		cout << "\nAdditional search paths:\n";
		cout << "  Language definitions:   "<<dataDir.getAdditionalLangDefDir() <<"\n";
		cout << "  Colour themes:          "<<dataDir.getAdditionalThemeDir() <<"\n";
// cout << "  Indentation schemes:    "<<dataDir.getAdditionalIndentSchemesDir()<<"\n";
	}

	cout << "\nConfiguration paths:\n";
	cout << "  Configuration files:    "<<dataDir.getConfDir ( true ) <<"\n";
	cout << "  User configuration:     "<<configFile<<"\n";
	if ( !dataDir.getAdditionalConfDir().empty() )
	{
		cout << "\nAdditional search paths:\n";
		cout << "  Configuration files:    "<<dataDir.getAdditionalConfDir() <<"\n";
	}
	cout << endl;
#ifdef HL_DATA_DIR
	cout << "Compiler directive HL_DATA_DIR = " <<HL_DATA_DIR<< "\n";
#endif
#ifdef HL_CONFIG_DIR
	cout << "Compiler directive HL_CONFIG_DIR = " <<HL_CONFIG_DIR<< "\n";
#endif

	cout << endl;
}

string HLCmdLineApp::getFileSuffix ( const string &fileName )
{
	size_t ptPos=fileName.rfind ( "." );
	return ( ptPos == string::npos ) ? "" : fileName.substr ( ptPos+1, fileName.length() );
}

bool HLCmdLineApp::loadFileTypeConfig ( const string& name, StringMap* extMap, StringMap* shebangMap )
{
	if ( !extMap || !shebangMap ) return false;
	string confPath=dataDir.getConfDir() + name + ".conf";
	ConfigurationReader config ( confPath );
	if ( config.found() )
	{
		stringstream values;
		string paramName, paramVal;
		for ( unsigned int i=0;i<config.getParameterNames().size();i++ )
		{
			paramName = config.getParameterNames() [i];

			if ( paramName.find ( "ext" ) != string::npos )
			{
				values.str ( StringTools::change_case ( config.getParameter ( paramName ) ) );
				paramName = StringTools::getParantheseVal ( paramName );
				while ( values >> paramVal )
				{
					extMap->insert ( make_pair ( paramVal,  paramName ) );
				}
				values.clear();
			}
			else if ( paramName.find ( "shebang" ) != string::npos )
			{
				values.str ( config.getParameter ( paramName ) ) ;
				paramName = StringTools::getParantheseVal ( paramName );
				while ( values >> paramVal )
				{
					shebangMap->insert ( make_pair ( paramVal,  paramName ) );
				}
				values.clear();
			}
		}
		return true;
	}
	else
	{
		cerr << "highlight: Configuration file "<< confPath << " not found.\n";
		return false;
	}
}


int HLCmdLineApp::getNumDigits ( int i )
{
	int res=0;
	while ( i )
	{
		i/=10;
		++res;
	}
	return res;
}

void HLCmdLineApp::printProgressBar ( int total, int count )
{
	if ( !total ) return;
	int p=100*count / total;
	int numProgressItems=p/10;
	cout << "\r[";
	for ( int i=0;i<10;i++ )
	{
		cout << ( ( i<numProgressItems ) ?"#":" " );
	}
	cout<< "] " <<setw ( 3 ) <<p<<"%, "<<count << " / " << total << "  " <<flush;
	if ( p==100 )
	{
		cout << endl;
	}
}

void HLCmdLineApp::printCurrentAction ( const string&outfilePath,
                                        int total, int count, int countWidth )
{
	cout << "Writing file "
	<< setw ( countWidth ) << count
	<< " of "
	<< total
	<< ": "
	<< outfilePath
	<< "\n";
}

void HLCmdLineApp::printIOErrorReport ( unsigned int numberErrorFiles,
                                        vector<string> & fileList,
                                        const string &action )
{
	cerr << "highlight: Could not "
	<< action
	<< " file"
	<< ( ( numberErrorFiles>1 ) ?"s":"" ) <<":\n";
	copy ( fileList.begin(), fileList.end(), ostream_iterator<string> ( cerr, "\n" ) );
	if ( fileList.size() < numberErrorFiles )
	{
		cerr << "... ["
		<< ( numberErrorFiles - fileList.size() )
		<< " of "
		<< numberErrorFiles
		<< " failures not shown, use --"
		<< OPT_VERBOSE
		<< " switch to print all failures]\n";
	}
}

string HLCmdLineApp::analyzeFile ( const string& file )
{
	string firstLine;

	if ( !file.empty() )
	{
		ifstream inFile ( file.c_str() );
		getline ( inFile, firstLine );
	}
	else
	{
		//  This copies all the data to a new buffer, uses the data to get the
		//  first line, then makes cin use the new buffer that underlies the
		//  stringstream instance
		cin_bufcopy << cin.rdbuf();
		getline ( cin_bufcopy, firstLine );
		cin_bufcopy.seekg ( 0, ios::beg );
		cin.rdbuf ( cin_bufcopy.rdbuf() );
	}
	StringMap::iterator it;
	for ( it=scriptShebangs.begin(); it!=scriptShebangs.end();it++ )
	{
		if ( Pattern::matches ( it->first, firstLine ) ) return it->second;
	}
	return "";
}

string HLCmdLineApp::guessFileType ( const string& suffix, const string &inputFile )
{
	string lcSuffix = StringTools::change_case ( suffix );
	string fileType = ( extensions.count ( lcSuffix ) ) ? extensions[lcSuffix] : lcSuffix ;
	if ( !fileType.empty() ) return fileType;
	return analyzeFile ( inputFile );
}


int HLCmdLineApp::run ( const int argc, const char*argv[] )
{

	CmdLineOptions options ( argc, argv );

	// set data directory path, where /langDefs and /themes reside
	string dataDirPath = ( options.getDataDir().empty() ) ?  Platform::getAppPath() :options.getDataDir();

	if ( options.printVersion() )
	{
		printVersionInfo();
		return EXIT_SUCCESS;
	}




	dataDir.setAdditionalDataDir ( options.getAdditionalDataDir() );
	dataDir.setAdditionalConfDir ( options.getAdditionalConfDir() );

	if ( ! dataDir.searchDataDir ( dataDirPath ) )
	{
		printBadInstallationInfo();
		return EXIT_FAILURE;
	}

	if ( options.printHelp() )
	{
		Help::printHelp();
		return EXIT_SUCCESS;
	}

	if ( options.printConfigInfo() )
	{
		printConfigInfo ( options.getConfigFilePath() );
		return EXIT_SUCCESS;
	}

	if ( options.showThemes() )
	{
		return printInstalledThemes() ?EXIT_SUCCESS:EXIT_FAILURE;
	}

	//call before printInstalledLanguages!
	loadFileTypeConfig ( "filetypes", &extensions, &scriptShebangs );

	if ( options.showLangdefs() )
	{
		return printInstalledLanguages() ?EXIT_SUCCESS:EXIT_FAILURE;
	}	

	const  vector <string> inFileList=options.getInputFileNames();

	if ( options.enableBatchMode() && inFileList[0].empty() )
	{
		return EXIT_FAILURE;
	}

	string themePath=dataDir.getThemePath ( options.getThemeName() );

//  auto_ptr<highlight::CodeGenerator> generator ( highlight::CodeGenerator::getInstance ( options.getOutputType() ) );      //2023/06/23//~3623R~
    unique_ptr<highlight::CodeGenerator> generator ( highlight::CodeGenerator::getInstance ( options.getOutputType() ) );    //2023/06/23//~3623I~


	generator->setHTMLAttachAnchors ( options.attachLineAnchors() );
	generator->setHTMLOrderedList ( options.orderedList() );
	generator->setHTMLInlineCSS ( options.inlineCSS() );
	generator->setHTMLEnclosePreTag ( options.enclosePreTag() );
	generator->setHTMLAnchorPrefix ( options.getAnchorPrefix() );
	generator->setHTMLClassName ( options.getClassName() );

	generator->setLATEXReplaceQuotes ( options.replaceQuotes() );
	generator->setLATEXNoShorthands ( options.disableBabelShorthands() );
	generator->setLATEXPrettySymbols ( options.prettySymbols() );

	generator->setRTFPageSize ( options.getPageSize() );
	generator->setRTFCharStyles ( options.includeCharStyles() );

	generator->setSVGSize ( options.getSVGWidth(),  options.getSVGHeight() );

	if (options.useCRDelimiter())
		 generator->setEOLDelimiter('\r');

	generator->setValidateInput ( options.validateInput() );
	generator->setStyleInputPath ( options.getStyleInFilename() );
	generator->setStyleOutputPath ( options.getStyleOutFilename() );
	generator->setIncludeStyle ( options.includeStyleDef() );
	generator->setPrintLineNumbers ( options.printLineNumbers(), options.getNumberStart() );
	generator->setPrintZeroes ( options.fillLineNrZeroes() );
	generator->setFragmentCode ( options.fragmentOutput() );
	generator->setPreformatting ( options.getWrappingStyle(),
	                              ( generator->getPrintLineNumbers() ) ?
	                              options.getLineLength() - options.getNumberWidth() : options.getLineLength(),
	                              options.getNumberSpaces() );

	generator->setEncoding ( options.getEncoding() );
	generator->setBaseFont ( options.getBaseFont() ) ;
	generator->setBaseFontSize ( options.getBaseFontSize() ) ;
	generator->setLineNumberWidth ( options.getNumberWidth() );
	generator->setStartingNestedLang( options.getStartNestedLang());
	generator->disableTrailingNL(options.disableTrailingNL());

	bool styleFileWanted = !options.fragmentOutput() || options.styleOutPathDefined();

	if ( !generator->initTheme ( themePath ) )
	{
		cerr << "highlight: Could not find style "
		<< themePath
		<< ".\n";
		return EXIT_FAILURE;
	}

	if ( options.printOnlyStyle() )
	{
		if (!options.formatSupportsExtStyle()){
			cerr << "highlight: output format supports no external styles.\n";
			return EXIT_FAILURE;
		}
		bool useStdout =  options.getStyleOutFilename() =="stdout";
		string cssOutFile=options.getOutDirectory()  + options.getStyleOutFilename();
		bool success=generator->printExternalStyle ( useStdout?"":cssOutFile );
		if ( !success )
		{
			cerr << "highlight: Could not write " << cssOutFile <<".\n";
			return EXIT_FAILURE;
		}
		return EXIT_SUCCESS;
	}

	bool formattingEnabled = generator->initIndentationScheme ( options.getIndentScheme() );

	if ( !formattingEnabled && !options.getIndentScheme().empty() )
	{
		cerr << "highlight: Undefined indentation scheme "
		<< options.getIndentScheme()
		<< ".\n";
		return EXIT_FAILURE;
	}

	if ( !options.getTagsFile().empty() )
	{
		if ( !generator->initTagInformation ( options.getTagsFile() ) )
		{
			cerr << "highlight: Could not load ctags file "
			<< options.getTagsFile()
			<< ".\n";
			return EXIT_FAILURE;
		}
	}

	string outDirectory = options.getOutDirectory();
#ifndef WIN32
	ifstream dirTest ( outDirectory.c_str() );
	if ( !outDirectory.empty() && !options.quietMode() && !dirTest )
	{
		cerr << "highlight: Output directory \""
		<< outDirectory
		<< "\" does not exist.\n";
		return EXIT_FAILURE;
	}
	dirTest.close();
#endif

	map <int,string> markedLines = options.getMarkLines();
	if ( !markedLines.empty() )
	{
		map<int, string>::iterator it;
		for ( it=markedLines.begin(); it!=markedLines.end();it++ )
		{
			generator->addMarkedLine ( it->first, it->second );
		}
	}

	bool initError=false, IOError=false;
	unsigned int fileCount=inFileList.size(),
	            fileCountWidth=getNumDigits ( fileCount ),
	            i=0,
	            numBadFormatting=0,
	            numBadInput=0,
	           numBadOutput=0;

	vector<string> badFormattedFiles, badInputFiles, badOutputFiles;
	string inFileName, outFilePath;
	string suffix, lastSuffix;

	if ( options.syntaxGiven() )  // user defined language definition, valid for all files
	{
		suffix = guessFileType ( options.getLanguage() );
	}

	while ( i < fileCount && !initError )
	{
		if ( !options.syntaxGiven() )  // determine file type for each file
		{
			suffix = guessFileType ( getFileSuffix ( inFileList[i] ), inFileList[i] );
		}
		if ( suffix.empty() )
		{
			if ( !options.enableBatchMode() )
				cerr << "highlight: Undefined language definition. Use --"
				<< OPT_SYNTAX << " option.\n";
			if ( !options.forceOutput() )
			{
				initError = true;
				break;
			}
		}

		if ( suffix != lastSuffix )
		{
			string langDefPath=dataDir.getLangPath ( suffix+".lang" );
			highlight::LoadResult loadRes= generator->loadLanguage ( langDefPath );
			if ( loadRes==highlight::LOAD_FAILED_REGEX )
			{
				cerr << "highlight: Regex error ( "
				<< generator->getLanguage().getFailedRegex()
				<< " ) in "<<suffix<<".lang\n";
				initError = true;
				break;
			}
			else if ( loadRes==highlight::LOAD_FAILED )
			{
				// do also ignore error msg if --syntax parameter should be skipped
				if ( ! (options.quietMode() || options.isSkippedExt ( suffix )) )
				{
					cerr << "highlight: Unknown source file extension \""
					<< suffix
					<< "\".\n";
				}
				if ( !options.forceOutput() )
				{
					initError = true;
					break;
				}
			}
			if ( options.printDebugInfo() && loadRes==highlight::LOAD_NEW )
			{
				printDebugInfo ( generator->getLanguage(), langDefPath );
			}
			lastSuffix = suffix;
		}

		string::size_type pos= ( inFileList[i] ).find_last_of ( Platform::pathSeparator );
		inFileName = inFileList[i].substr ( pos+1 );

		if ( options.enableBatchMode() )
		{
			outFilePath = outDirectory;
			outFilePath += inFileName;
			outFilePath += options.getOutFileSuffix();

			if ( !options.quietMode() )
			{
				if ( options.printProgress() )
				{
					printProgressBar ( fileCount, i+1 );
				}
				else
				{
					printCurrentAction ( outFilePath, fileCount, i+1, fileCountWidth );
				}
			}
		}
		else
		{
			outFilePath = options.getSingleOutFilename();
			if ( outFilePath.size() && outFilePath==options.getSingleInFilename() )
			{
				cerr 	<< "highlight: Output path equals input path: \""
				<< outFilePath << "\".\n";
				initError = true;
				break;
			}

		}

		if ( options.useFNamesAsAnchors() )
		{
			generator->setHTMLAnchorPrefix ( inFileName );
		}

		generator->setTitle ( options.getDocumentTitle().empty() ?
		                      inFileList[i]:options.getDocumentTitle() );

		generator->setKeyWordCase ( options.getKeywordCase() );

		highlight::ParseError error = generator->generateFile ( inFileList[i], outFilePath );

		if ( error==highlight::BAD_INPUT )
		{
			if ( numBadInput++ < IO_ERROR_REPORT_LENGTH || options.printDebugInfo() )
			{
				badInputFiles.push_back ( inFileList[i] );
			}
		}
		else if ( error==highlight::BAD_OUTPUT )
		{
			if ( numBadOutput++ < IO_ERROR_REPORT_LENGTH || options.printDebugInfo() )
			{
				badOutputFiles.push_back ( outFilePath );
			}
		}
		if ( formattingEnabled && !generator->formattingIsPossible() )
		{
			if ( numBadFormatting++ < IO_ERROR_REPORT_LENGTH || options.printDebugInfo() )
			{
				badFormattedFiles.push_back ( outFilePath );
			}
		}
		++i;
	}


	if ( i &&   !options.includeStyleDef()
	        && styleFileWanted
	        && options.formatSupportsExtStyle() )
	{
		string cssOutFile=outDirectory  + options.getStyleOutFilename();
		bool success=generator->printExternalStyle ( cssOutFile );
		if ( !success )
		{
			cerr << "highlight: Could not write " << cssOutFile <<".\n";
			IOError = true;
		}
	}

	if ( i && options.printIndexFile() )
	{
		bool success=generator -> printIndexFile ( inFileList, outDirectory );
		if ( !success )
		{
			cerr << "highlight: Could not write index file.\n";
			IOError = true;
		}
	}

	if ( numBadInput )
	{
		printIOErrorReport ( numBadInput, badInputFiles, "read input" );
		IOError = true;
	}
	if ( numBadOutput )
	{
		printIOErrorReport ( numBadOutput, badOutputFiles, "write output" );
		IOError = true;
	}
	if ( numBadFormatting )
	{
		printIOErrorReport ( numBadFormatting, badFormattedFiles, "reformat" );
	}
	return ( initError || IOError ) ? EXIT_FAILURE : EXIT_SUCCESS;
}

int main ( const int argc, const char *argv[] )
{
	HLCmdLineApp app;
	return app.run ( argc, argv );
}
