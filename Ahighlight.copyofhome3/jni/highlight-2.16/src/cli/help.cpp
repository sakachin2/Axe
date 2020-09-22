/***************************************************************************
                          help.cpp  -  description
                             -------------------
    begin                : Die Apr 23 2002
    copyright            : (C) 2002-2007 by Andre Simon
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


#include <iostream>

#include "help.h"

using namespace std;

namespace Help
{

	void printHelp()
	{
		cout<<"USAGE: highlight [OPTIONS]... [FILES]...\n";
		cout<<"\n";
		cout<<"General options:\n";
		cout<<"\n";
		cout<<" -B, --batch-recursive=<wc>     convert all matching files, searches subdirs\n";
		cout<<"                                  (Example: -B '*.cpp')\n";
		cout<<" -D, --data-dir=<directory>     set path to data directory\n";
		cout<<" -E, --add-data-dir=<directory> set path to an additional data directory, which\n";
		cout<<"                                  is searched first\n";
		cout<<"     --add-config-dir=<dir>     set path to an additional config directory\n";
		cout<<" -h, --help                     print this help\n";
		cout<<" -i, --input=<file>             name of single input file\n";
		cout<<" -o, --output=<file>            name of single output file\n";
		cout<<" -O, --outdir=<directory>       name of output directory\n";
		cout<<" -p, --list-langs               list installed language definitions\n";
		cout<<" -P, --progress                 print progress bar in batch mode\n";
		cout<<" -q, --quiet                    supress progress info in batch mode\n";
		cout<<" -S, --syntax=<type>            specify type of source code\n";
		cout<<" -v, --verbose                  print debug info\n";
		cout<<" -w, --list-themes              list installed colour themes\n";
		cout<<"     --force                    generate output if language type is unknown\n";
		cout<<"     --print-config             print path configuration\n";
		cout<<"     --print-style              print only style (see --style-outfile)\n";
		cout<<"     --skip=<list>              ignore listed unknown file types\n";
		cout<<"                                  (Example: --skip='bak;c~;h~')\n";
		cout<<"     --start-nested=<lang>      define nested language which starts input\n";
		cout<<"                                  without opening delimiter\n";
		cout<<"     --validate-input           test if input is a valid text file\n";
		cout<<"     --version                  print version and copyright information\n";
		cout<<"\n";
		cout<<"\n";
		cout<<"Output formats:\n";
		cout<<"\n";
		cout<<" -H, --html                     generate HTML (default)\n";
		cout<<" -A, --ansi                     generate terminal output (16 colours)\n";
		cout<<" -L, --latex                    generate LaTeX\n";
		cout<<" -M, --xterm256                 generate terminal output (256 colours)\n";
		cout<<" -R, --rtf                      generate RTF\n";
		cout<<" -T, --tex                      generate TeX\n";
		cout<<" -X, --xhtml                    generate XHTML 1.1\n";
		cout<<" -Z, --xml                      generate XML\n";
		cout<<" -G, --svg                      generate SVG (experimental)\n";
		cout<<" -Y, --bbcode                   generate BBCode (experimental)\n";
		cout<<"     --out-format=<format>      output file in given format\n";
		cout<<"                                  <format>: see long options above\n";
		cout<<"\n";
		cout<<"\n";
		cout<<"Output formatting options:\n";
		cout<<"\n";
		cout<<" -c, --style-outfile=<file>     name of style file or output to stdout, if\n";
		cout<<"                                  'stdout' is given as file argument\n";
		cout<<" -d, --doc-title=<title>        document title\n";
		cout<<" -e, --style-infile=<file>      file to be included in style-outfile\n";
		cout<<" -I, --include-style            include style definition\n";
		cout<<" -f, --fragment                 omit file header and footer\n";
		cout<<" -F, --reformat=<style>         reformats and indents output in given style\n";
		cout<<"                                  <style>=['allman', 'banner', 'gnu',\n";
		cout<<"                                  'horstmann', 'java', 'kr', 'linux', 'otbs',\n";
		cout<<"                                  'stroustrup', 'whitesmith']\n";
		cout<<" -J, --line-length=<num>        line length before wrapping (see -W, -V)\n";
		cout<<" -j, --line-number-length=<num> line number width incl. left padding\n";
		cout<<" -k, --font=<font>              set font (specific to output format)\n";
		cout<<" -K, --font-size=<num?>         set font size (specific to output format)\n";
		cout<<" -l, --linenumbers              print line numbers in output file\n";
		cout<<" -m, --line-number-start=<cnt>  start line numbering with cnt (assumes -l)\n";
		cout<<" -s, --style=<style>            set colour style (see -w)\n";
		cout<<" -t, --replace-tabs=<num>       replace tabs by <num> spaces\n";
		cout<<" -u, --encoding=<enc>           set output encoding which matches input file\n";
		cout<<"                                  encoding; omit encoding info if enc=NONE\n";
		cout<<" -V, --wrap-simple              wrap long lines without indenting function\n";
		cout<<"                                  parameters and statements\n";
		cout<<" -W, --wrap                     wrap long lines\n";
		cout<<" -z, --zeroes                   fill leading space of line numbers with 0's\n";
		cout<<"     --kw-case=<case>           change case of case insensitive keywords\n";
		cout<<"                                  <case> =  ['upper', 'lower', 'capitalize']\n";
		cout<<"     --delim-cr                 set CR as end-of-line delimiter (MacOS 9)\n";
		cout<<"     --no-trailing-nl           omit trailing newline\n"; 
		cout<<"\n";
		cout<<"(X)HTML output options:\n";
		cout<<"\n";
		cout<<" -a, --anchors                  attach anchor to line numbers\n";
		cout<<" -y, --anchor-prefix=<str>      set anchor name prefix\n";
		cout<<" -N, --anchor-filename          use input file name as anchor name\n";
		cout<<" -C, --print-index              print index with hyperlinks to output files\n";
		cout<<" -n, --ordered-list             print lines as ordered list items\n";
		cout<<"     --class-name=<str>         set CSS class name prefix\n";
		cout<<"     --inline-css               output CSS within each tag (verbose output)\n";
		cout<<"     --mark-line='n[=txt]; m'   mark given lines n..m and add optional help\n";
		cout<<"                                  texts as tooltips\n";
		cout<<"     --enclose-pre              enclose fragmented output with pre tag \n";
		cout<<"                                  (assumes -f)\n";
		cout<<"     --ctags-file[=<file>]      read ctags file to include meta information as\n";
		cout<<"                                  tooltips (default value: tags)\n";
		cout<<"\n";
		cout<<"LaTeX output options:\n";
		cout<<"\n";
		cout<<" -b, --babel                    disable Babel package shorthands\n";
		cout<<" -r, --replace-quotes           replace double quotes by \\dq{}\n";
		cout<<"     --pretty-symbols           improve appearance of brackets and other symbols\n";
		cout<<"\n";
		cout<<"\n";
		cout<<"RTF output options:\n";
		cout<<"\n";
		cout<<" -x, --page-size=<ps>           set page size \n";
		cout<<"                                  <ps> = [a3, a4, a5, b4, b5, b6, letter]\n";
		cout<<"     --char-styles              include character stylesheets\n";
		cout<<"\n";
		cout<<"\n";
		cout<<"SVG output options:\n";
		cout<<"\n";
		cout<<"     --height                   set image height (units allowed)\n";
		cout<<"     --width                    set image width (see --height)\n";
		cout<<"\n";
		cout<<"\n";
		cout<<"GNU source-highlight compatibility options:\n";
		cout<<"\n";
		cout<<"     --doc                      create stand alone document\n";
		cout<<"     --no-doc                   cancel the --doc option\n";
		cout<<"     --css=filename             the external style sheet filename\n";
		cout<<"     --src-lang=STRING          source language\n";
		cout<<" -t, --tab=INT                  specify tab length\n";
		cout<<" -n, --line-number[=0]          number all output lines, optional padding\n";
		cout<<"     --line-number-ref[=p]      number all output lines and generate an anchor,\n";
		cout<<"                                  made of the specified prefix p + the line\n";
		cout<<"                                  number  (default='line')\n";
		cout<<"     --output-dir=path          output directory\n";
		cout<<"     --failsafe                 if no language definition is found for the\n";
		cout<<"                                  input, it is simply copied to the output\n";
		cout<<"\n";
		cout<<"\n";
		cout<<"-t will be ignored if -F is set.\n";
		cout<<"-i and -o will be ignored if -b or -B is set.\n";
		cout<<"-r will be ignored if -f is not set.\n";
		cout<<"-c will be ignored if the output format does not support referenced styles.\n";
		cout<<"If no in- or output files are specified, stdin and stdout will be used for\n";
		cout<<"in- or output.\n";
		cout<<"HTML will be generated, if no other output format is given. Style definitions\n";
		cout<<"are stored in highlight.css (HTML, XHTML, SVG) or highlight.sty (LaTeX, TeX)\n";
		cout<<"if neither -c nor -I is given.\n";
		cout<<"Reformatting code will only work with C, C++, C# and Java input files.\n";
		cout<<"Wrapping lines with -V or -W will cause faulty highlighting of long single\n";
		cout<<"line comments and directives. Use with caution.\n";
		cout<<"\n";
		cout<<"Updates and information: http://www.andre-simon.de/\n";
	}

}
