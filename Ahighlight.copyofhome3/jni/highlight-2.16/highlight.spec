#%define prefix  /usr

# Release number can be specified with rpmbuild --define 'rel SOMETHING' ...
# If no such --define is used, the release number is 1.
#
# Source archive's extension can be specified with --define 'srcext .foo'
# where .foo is the source archive's actual extension.
# To compile an RPM from a .bz2 source archive, give the command
#   rpmbuild -tb --define 'srcext bz2' @PACKAGE@-@VERSION@.tar.bz2
#
#%if %{?rel:0}%{!?rel:1}
%define rel 1
#%endif

%if %{?srcext:0}%{!?srcext:1}
%define srcext bz2
%endif

Name:    highlight
Summary: Universal source code to formatted text converter
Version: 2.16
Release: %{rel}
Group:   Development/Tools
License: GPLv2
Vendor:  Andre Simon <andre.simon1@gmx.de>
URL:     http://www.andre-simon.de/

Source0:	http://www.andre-simon.de/zip/%{name}-%{version}.tar.%{srcext}
Buildroot:      %{_tmppath}/%{name}-%{version}.%{release}-root
BuildRequires:	qt-devel

%package gui
Summary: The optional highlight Qt GUI
Group: Development/Tools
%description gui
A intuitive user interface of the highlight utility.

%description
A utility that converts sourcecode to HTML, XHTML, RTF, LaTeX, TeX, XML, BBCode or
terminal escape sequences with syntax highlighting.
It supports about 140 programming and markup languages.
Language descriptions are configurable and support regular expressions.
The utility offers indentation and reformatting capabilities.
It is easily possible to create new language definitions and colour themes.

%prep
%setup -q -n highlight-%{version}

%build
make cli
make gui

%install
if [ -d $RPM_BUILD_ROOT ]; then rm -r $RPM_BUILD_ROOT; fi
install -d $RPM_BUILD_ROOT%
install -d $RPM_BUILD_ROOT%{_datadir}/highlight/themes $RPM_BUILD_ROOT%{_datadir}/highlight/langDefs
install -d $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/examples/plugins/dokuwiki
install -d $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/examples/plugins/movabletype
install -d $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/examples/plugins/wordpress
install -d $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/examples/plugins/serendipity_event_highlight
install -d $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/examples/swig

install -d $RPM_BUILD_ROOT/etc/highlight/
install -d $RPM_BUILD_ROOT%{_mandir}/man1
install -d $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/

install -m644 ./man/highlight.1.gz $RPM_BUILD_ROOT%{_mandir}/man1/highlight.1.gz
install -m644 ./langDefs/*.lang  $RPM_BUILD_ROOT%{_datadir}/highlight/langDefs/
install -m644 ./*.conf $RPM_BUILD_ROOT/etc/highlight/
install -m644 ./themes/*.style $RPM_BUILD_ROOT%{_datadir}/highlight/themes/
install -m644 ./examples/plugins/dokuwiki/* $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/examples/plugins/dokuwiki
install -m644 ./examples/plugins/movabletype/* $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/examples/plugins/movabletype
install -m644 ./examples/plugins/wordpress/* $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/examples/plugins/wordpress
install -m644 ./examples/plugins/serendipity_event_highlight/* $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/examples/plugins/serendipity_event_highlight
install -m644 ./examples/swig/* $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/examples/swig
install -m644 ./examples/highlight_pipe.* $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/examples
install -m644 ./ChangeLog ./AUTHORS ./COPYING ./TODO ./README ./README_DE ./README_REGEX ./README_LANGLIST ./INSTALL  $RPM_BUILD_ROOT%{_defaultdocdir}/highlight/
mkdir -p $RPM_BUILD_ROOT%{_bindir}
install -m755 ./src/highlight  $RPM_BUILD_ROOT%{_bindir}

install -d $RPM_BUILD_ROOT%{_datadir}/highlight/gui_files/ext $RPM_BUILD_ROOT%{_datadir}/highlight/gui_files/l10n
install -m644 ./gui_files/l10n/*  $RPM_BUILD_ROOT%{_datadir}/highlight/gui_files/l10n/
install -m644 ./gui_files/ext/*  $RPM_BUILD_ROOT%{_datadir}/highlight/gui_files/ext/
install -m755 ./src/highlight-gui  $RPM_BUILD_ROOT%{_bindir}

%clean
rm -fr %{buildroot}

%postun
rmdir  %{_datadir}/highlight/themes  %{_datadir}/highlight/indentSchemes %{_datadir}/highlight/langDefs %{_datadir}/highlight/helpmsg
rmdir --ignore-fail-on-non-empty %{_datadir}/highlight

%postun gui
rmdir  %{_datadir}/highlight/gui_files/l10n %{_datadir}/highlight/gui_files/ext
rmdir  %{_datadir}/highlight/gui_files
rmdir  --ignore-fail-on-non-empty %{_datadir}/highlight

%files
%defattr(-,root,root,-)

%{_defaultdocdir}/highlight
/etc/highlight/*.conf
%{_datadir}/highlight/langDefs/*.lang
%{_datadir}/highlight/themes/*.style
%{_mandir}/man1/highlight.1.gz
%{_bindir}/highlight

%files gui
%{_datadir}/highlight/gui_files/ext/*
%{_datadir}/highlight/gui_files/l10n/*
%{_bindir}/highlight-gui

%changelog
* Tue Feb 26 2002 Andre Simon <andre.simon1@gmx.de>
- Initial build

