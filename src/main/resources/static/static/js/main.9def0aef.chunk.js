(this.webpackJsonpnovlr=this.webpackJsonpnovlr||[]).push([[0],{26:function(t,e,n){},46:function(t,e,n){},48:function(t,e,n){},49:function(t,e,n){},50:function(t,e,n){},51:function(t,e,n){},52:function(t,e,n){"use strict";n.r(e);var o=n(2),a=n.n(o),r=n(21),i=n.n(r),s=(n(26),n(12)),c=n(3),u=n.n(c),l=n(10),h=n(8),d=n(4),b=n(5),p=n(7),f=n(6),k=n(9),v=n.n(k),g=(n(46),n(0)),j=function(t){Object(p.a)(n,t);var e=Object(f.a)(n);function n(t){var o;return Object(d.a)(this,n),(o=e.call(this,t)).authorChange=function(t){return o.setState({author:t.target.value})},o.titleChange=function(t){return o.setState({title:t.target.value})},o.bookContentChange=function(t){var e;return o.setState({bookContent:null===(e=t.target)||void 0===e?void 0:e.value})},o.setFormWanings=function(t){return o.setState({formWarnings:t})},o.submit=function(){var t=o.state,e=t.author,n=t.title,a=t.bookContent;if(""!==e&&""!==n&&""!==a){var r={author:e,title:n,paragraphs:a.split("\n")};console.log(r),o.props.submitBook(r),o.props.setPage("BOOKS")}else{var i={validAuthor:""!==e,validTitle:""!==n,validBookContent:""!==a};o.setFormWanings(i)}},o.state={author:"",title:"",bookContent:"",formWarnings:{validAuthor:!0,validTitle:!0,validBookContent:!0}},o}return Object(b.a)(n,[{key:"render",value:function(){var t=this,e=this.state,n=e.formWarnings,o=e.author,a=e.title,r=e.bookContent;return Object(g.jsxs)("div",{className:"submit-booklet",children:[Object(g.jsx)("p",{className:"book-list-header",children:"Add the beginning of a book"}),Object(g.jsx)("input",{className:"author-name ".concat(n.validAuthor?"":"has-error"),placeholder:"Author",value:o,onChange:function(e){return t.authorChange(e)}}),Object(g.jsx)("input",{className:"book-title ".concat(n.validTitle?"":"has-error"),placeholder:"Book Title",value:a,onChange:function(e){return t.titleChange(e)}}),Object(g.jsx)("textarea",{className:"book-content ".concat(n.validBookContent?"":"has-error"),maxLength:"1000",placeholder:"First 1000 characters",value:r,onChange:function(e){return t.bookContentChange(e)}}),Object(g.jsx)("button",{className:"submit-book",onClick:function(){return t.submit()},children:"Submit"})]})}}]),n}(o.Component),m=(n(48),function(t){Object(p.a)(n,t);var e=Object(f.a)(n);function n(t){var o;return Object(d.a)(this,n),(o=e.call(this,t)).componentDidMount=function(){o.convertParagraphsToContent()},o.authorChange=function(t){return o.setState({author:t.target.value})},o.titleChange=function(t){return o.setState({title:t.target.value})},o.bookContentChange=function(t){var e;return o.setState({bookContent:null===(e=t.target)||void 0===e?void 0:e.value})},o.edit=function(){var t={author:o.state.author,title:o.state.title,paragraphs:o.state.bookContent.split("\n")};o.props.editBook(t,o.state.id)},o.convertParagraphsToContent=function(){var t="";o.state.paragraphs.forEach((function(e,n){t+=e,n<o.state.paragraphs.length-1&&(t+="\n")})),o.setState({bookContent:t})},o.state={id:t.book.id,author:t.book.author,title:t.book.title,paragraphs:t.book.paragraphs,bookContent:""},o}return Object(b.a)(n,[{key:"render",value:function(){var t=this;return Object(g.jsxs)("div",{className:"editable-book",children:[Object(g.jsx)("input",{className:"author-name",placeholder:"Author",value:this.state.author,onChange:function(e){return t.authorChange(e)}}),Object(g.jsx)("input",{className:"book-name",placeholder:"Title",value:this.state.title,onChange:function(e){return t.titleChange(e)}}),Object(g.jsx)("textarea",{className:"editable-book-content",maxLength:"1000",placeholder:"First 1000 characters",value:this.state.bookContent,onChange:function(e){return t.bookContentChange(e)}}),Object(g.jsxs)("div",{className:"button-wrapper",children:[Object(g.jsx)("button",{onClick:function(){return t.edit()},children:"Edit"}),Object(g.jsx)("button",{onClick:function(){return t.props.deleteBook(t.state.id)},children:"Delete"})]})]})}}]),n}(o.Component)),O=(n(49),function(t){Object(p.a)(n,t);var e=Object(f.a)(n);function n(t){var o;return Object(d.a)(this,n),(o=e.call(this,t)).editBook=function(t,e){o.props.editBook(t,e),o.setEditable(!1)},o.deleteBook=function(t){o.props.deleteBook(t),o.setEditable(!1)},o.setView=function(){return o.setEditable(o.props.isAdmin)},o.setEditable=function(t){return o.setState({editable:t})},o.getViewClass=function(){return o.props.isAdmin?"editable book":"book"},o.state={editable:!1},o}return Object(b.a)(n,[{key:"render",value:function(){var t,e=this.props.book,n=Object(g.jsxs)("div",{className:this.getViewClass(),onClick:this.setView,children:[Object(g.jsx)("div",{className:"paragraph-wrapper",children:null===(t=e.paragraphs)||void 0===t?void 0:t.map((function(t,n){return Object(g.jsx)("p",{className:"paragraph",children:t},"".concat(e.id,"-").concat(n))}))}),Object(g.jsxs)("div",{className:"author-title",children:[Object(g.jsx)("span",{className:"author-label",children:"".concat(e.author,", ")}),Object(g.jsx)("span",{className:"title-label",children:e.title})]}),Object(g.jsx)("div",{className:"book-border"})]});return Object(g.jsx)("div",{className:"book-view",children:this.state.editable?Object(g.jsx)(m,{book:e,editBook:this.editBook,deleteBook:this.deleteBook}):n})}}]),n}(o.Component));n(50);var B=function(t){var e=t.setPage,n=t.books,o=t.isAdmin,a=t.deleteBook,r=t.editBook;return Object(g.jsxs)("div",{className:"book-list-wrapper",children:[Object(g.jsx)("div",{className:"book-list-header",onClick:function(){return e("SUBMIT")},children:"Add the beginning of a book"}),null===n||void 0===n?void 0:n.map((function(t){return Object(g.jsx)(O,{book:t,isAdmin:o,deleteBook:a,editBook:r},t.id)}))]})},C=(n(51),function(t){Object(p.a)(n,t);var e=Object(f.a)(n);function n(t){var o;return Object(d.a)(this,n),(o=e.call(this,t)).baseApiUrl="/api",o.componentDidMount=function(){o.getBooks(),o.increasePageNumber(),o.setIsAdmin(),window.addEventListener("scroll",o.handleScroll)},o.componentWillUnmount=function(){return window.removeEventListener("scroll",o.handleScroll)},o.getBooks=Object(h.a)(u.a.mark((function t(){return u.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(!o.state.hasMoreBooks){t.next=3;break}return t.next=3,v.a.get("".concat(o.baseApiUrl,"/").concat(o.state.pageNumber)).then((function(t){var e=t.data,n=o.state.books.map((function(t){return t.id})),a=e.books.filter((function(t){return!n.includes(t.id)}));o.setBooks([].concat(Object(l.a)(o.state.books),Object(l.a)(a))),o.setHasMoreBooks(e.hasMoreBooks)})).catch((function(t){return console.log("ERROR RETRIEVING BOOKS",o.state.pageNumber)}));case 3:case"end":return t.stop()}}),t)}))),o.submitBook=function(){var t=Object(h.a)(u.a.mark((function t(e){return u.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",v.a.post(o.baseApiUrl,Object(s.a)({},e)).then((function(t){var e=t.data;return o.setBooks([].concat(Object(l.a)(o.state.books),[e]))})).catch((function(t){return console.log("ERROR SUMBITTING BOOK")})));case 1:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}(),o.editBook=function(){var t=Object(h.a)(u.a.mark((function t(e,n){return u.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",v.a.patch("".concat(o.baseApiUrl,"/").concat(n),Object(s.a)({},e)).then((function(t){var e,n=t.data;return o.setBooks(Object(l.a)(null===(e=o.state.books)||void 0===e?void 0:e.map((function(t){return t.id===n.id?n:t}))))})).catch((function(t){return console.log("ERROR EDITING BOOK")})));case 1:case"end":return t.stop()}}),t)})));return function(e,n){return t.apply(this,arguments)}}(),o.deleteBook=function(){var t=Object(h.a)(u.a.mark((function t(e){return u.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",v.a.delete("".concat(o.baseApiUrl,"/").concat(e),{id:e}).then((function(){return o.setBooks(o.state.books.filter((function(t){return t.id!==e})))})).catch((function(t){return console.log("ERROR DELETING BOOK")})));case 1:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}(),o.handleScroll=function(){Math.ceil(window.scrollY+window.innerHeight+2)>=document.body.offsetHeight&&o.state.hasMoreBooks&&(o.getBooks(),o.increasePageNumber())},o.setBooks=function(t){return o.setState({books:t})},o.setPage=function(t){return o.setState({activePage:t})},o.setHasMoreBooks=function(t){return o.setState({hasMoreBooks:t})},o.increasePageNumber=function(){return o.setState({pageNumber:o.state.pageNumber+1})},o.resetPageNumber=function(){return o.setState({pageNumber:0})},o.sanitizeList=function(t){return null===t||void 0===t?void 0:t.filter((function(t){return t.author&&t.title&&t.paragraphs}))},o.setIsAdmin=function(){var t,e,n="haslewood".toLowerCase(),a=(null===(t=window.location.href.split("/"))||void 0===t?void 0:t.pop().toLowerCase())===n||(null===(e=window.location.href.split("="))||void 0===e?void 0:e.pop().toLowerCase())===n;o.setState({isAdmin:a})},o.state={activePage:"BOOKS",books:[],isAdmin:!1,hasMoreBooks:!0,pageNumber:0},o}return Object(b.a)(n,[{key:"render",value:function(){var t={SUBMIT:Object(g.jsx)(j,{submitBook:this.submitBook,setPage:this.setPage}),BOOKS:Object(g.jsx)(B,{isAdmin:this.state.isAdmin,books:this.sanitizeList(this.state.books),setPage:this.setPage,deleteBook:this.deleteBook,editBook:this.editBook})};return Object(g.jsx)("div",{className:"App",children:t[this.state.activePage]})}}]),n}(o.Component)),x=function(t){t&&t instanceof Function&&n.e(3).then(n.bind(null,53)).then((function(e){var n=e.getCLS,o=e.getFID,a=e.getFCP,r=e.getLCP,i=e.getTTFB;n(t),o(t),a(t),r(t),i(t)}))};i.a.render(Object(g.jsx)(a.a.StrictMode,{children:Object(g.jsx)(C,{})}),document.getElementById("root")),x()}},[[52,1,2]]]);
//# sourceMappingURL=main.9def0aef.chunk.js.map