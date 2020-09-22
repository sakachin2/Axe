/***************************************************************************
                          TexGenerator.cpp  -  description
                             -------------------
    begin                : Mit Jul 24 2002
    copyright            : (C) 2002 by Andre Simon
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


#include <sstream>

#include "charcodes.h"
#include "version.h"
#include "texgenerator.h"

namespace highlight
{

	TexGenerator::TexGenerator()
			: CodeGenerator ( TEX )
	{


		/*This makes TeX to use every par it encounters (the \\leavevmode has
		no effect when TeX is in horizontal mode and when TeX is in vertical
		mode, it switches it to horizontal mode).*/
		newLineTag="\\leavevmode\\par\n";

		spacer = "\\ ";
		maskWs=true;
		excludeWs=true;
		maskWsBegin = "{\\hlstd";
		maskWsEnd = "}";
		styleCommentOpen="%";
	}

	TexGenerator::~TexGenerator()
	{}

	void TexGenerator::initOutputTags(){
		openTags.push_back ( "{\\hl"+STY_NAME_STD+" " );
		openTags.push_back ( "{\\hl"+STY_NAME_STR+" " );
		openTags.push_back ( "{\\hl"+STY_NAME_NUM+" " );
		openTags.push_back ( "{\\hl"+STY_NAME_SLC+" " );
		openTags.push_back ( "{\\hl"+STY_NAME_COM+" " );
		openTags.push_back ( "{\\hl"+STY_NAME_ESC+" " );
		openTags.push_back ( "{\\hl"+STY_NAME_DIR+" " );
		openTags.push_back ( "{\\hl"+STY_NAME_DST+" " );
		openTags.push_back ( "{\\hl"+STY_NAME_LIN+" " );
		openTags.push_back ( "{\\hl"+STY_NAME_SYM+" " );

		for ( int i=0; i<NUMBER_BUILTIN_STATES; i++ )
		{
			closeTags.push_back ( "}" );
		}
	}

	string TexGenerator::getAttributes ( const string & elemName,const ElementStyle & elem )
	{
		ostringstream s;
		s << "\\def\\hl"
		<< elemName
		<< "{";
		if ( elem.isBold() )  s << "\\bf";
		if ( elem.isItalic() )  s << "\\it";
		s  <<  "\\textColor{"
		<< ( elem.getColour().getRed ( TEX ) ) <<" "
		<< ( elem.getColour().getGreen ( TEX ) ) <<" "
		<< ( elem.getColour().getBlue ( TEX ) ) <<" 0.0}}\n";
		return  s.str();
	}

	string TexGenerator::getHeader()
	{
		ostringstream os;
		os << styleCommentOpen
		<< " Document title: " << docTitle << "\n\n";
		if ( langInfo.highlightingEnabled() )
		{
			if ( includeStyleDef )
			{
				os << getStyleDefinition();
				os << CodeGenerator::readUserStyleDef();
			}
			else
			{
				os << "\\input "
				<< getStyleOutputPath()
				<< "\n\n";
			}
		}

		return os.str();
	}

	void TexGenerator::printBody()
	{
		*out << "{\n\\"<<getBaseFont() <<"\n";

		processRootState();

		*out << "}\n";
	}

	string TexGenerator::getFooter()
	{
		ostringstream os;
		os << "\\bye\n"
		<< "% TeX generated by Highlight "
		<< HIGHLIGHT_VERSION
		<< ", "
		<< HIGHLIGHT_URL
		<< endl;
		return os.str();
	}

	string TexGenerator:: maskCharacter ( unsigned char c )
	{
		switch ( c )
		{
			case '{':
			case '}':
			{
				string m ( "$\\" );
				m += c;
				m += '$';
				return m;
			}
			break;
			case '^':
				return "{\\bf\\^{}}";
				break;
			case '_':
				return "\\_{}";
				break;
			case '&':
			case '$':
			case '#':
			case '%':
			{
				string m ( "\\" );
				m += c;
				return m;
			}
			break;
			case '\\':
				return "$\\backslash$";
				break;
			case ' ':
				return spacer;
				break;
			case '+':
			case '-':
			case '<':
			case '>':
			case '=':
			{
				string m ( "$\\mathord{" );
				m += c;
				m += "}$";
				return m;
			}
			break;
			case AUML_LC:
				return "\\\"a";
				break;
			case OUML_LC:
				return "\\\"o";
				break;
			case UUML_LC:
				return "\\\"u";
				break;
			case AUML_UC:
				return "\\\"A";
				break;
			case OUML_UC:
				return "\\\"O";
				break;
			case UUML_UC:
				return "\\\"U";
				break;
			case AACUTE_LC:
				return "\\'a";
				break;
			case EACUTE_LC:
				return "\\'e";
				break;
			case OACUTE_LC:
				return "\\'o";
				break;
			case UACUTE_LC:
				return "\\'u";
				break;
			case AGRAVE_LC:
				return "\\`a";
				break;
			case EGRAVE_LC:
				return "\\`e";
				break;
			case OGRAVE_LC:
				return "\\`o";
				break;
			case UGRAVE_LC:
				return "\\`u";
				break;
			case AACUTE_UC:
				return "\\'A";
				break;
			case EACUTE_UC:
				return "\\'E";
				break;
			case OACUTE_UC:
				return "\\'O";
				break;
			case UACUTE_UC:
				return "\\'U";
				break;
			case AGRAVE_UC:
				return "\\`A";
				break;
			case EGRAVE_UC:
				return "\\`E";
				break;
			case UGRAVE_UC:
				return "\\`O";
				break;
			case OGRAVE_UC:
				return "\\`U";
				break;
			case SZLIG:
				return "\\ss ";
				break;

			default :
				return string ( 1, c );
		}
	}

	string TexGenerator::getKeywordOpenTag ( unsigned int styleID )
	{
		return "{\\hl"+langInfo.getKeywordClasses() [styleID]+" ";
	}

	string TexGenerator::getKeywordCloseTag ( unsigned int styleID )
	{
		return "}";
	}


	string TexGenerator::getStyleDefinition()
	{
		if ( styleDefinitionCache.empty() )
		{
			ostringstream os;
			os << getAttributes ( STY_NAME_STD, docStyle.getDefaultStyle() );
			os << getAttributes ( STY_NAME_NUM, docStyle.getNumberStyle() );
			os << getAttributes ( STY_NAME_ESC, docStyle.getEscapeCharStyle() );
			os << getAttributes ( STY_NAME_STR, docStyle.getStringStyle() );
			os << getAttributes ( STY_NAME_DST, docStyle.getDirectiveStringStyle() );
			os << getAttributes ( STY_NAME_SLC, docStyle.getSingleLineCommentStyle() );
			os << getAttributes ( STY_NAME_COM, docStyle.getCommentStyle() );
			os << getAttributes ( STY_NAME_DIR, docStyle.getDirectiveStyle() );
			os << getAttributes ( STY_NAME_LIN, docStyle.getLineStyle() );
			os << getAttributes ( STY_NAME_SYM, docStyle.getSymbolStyle() );

			KeywordStyles styles = docStyle.getKeywordStyles();
			for ( KSIterator it=styles.begin(); it!=styles.end(); it++ )
			{
				os << getAttributes ( it->first, it->second );
			}

			os <<  "% The special option is not supported by all dvi drivers\n"
			<< "\\special{background rgb "
			<< docStyle.getBgColour().getRed ( LATEX ) << " "
			<< docStyle.getBgColour().getGreen ( LATEX ) << " "
			<< docStyle.getBgColour().getBlue ( LATEX ) << "}";
			os << "\n\\nopagenumbers\n"
			<< "\\input colordvi\n";
			styleDefinitionCache=os.str();
		}
		return styleDefinitionCache;
	}


}