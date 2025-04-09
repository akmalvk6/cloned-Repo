export class SearchDTO {
    start: number;      // Pagination start
    pageSize: number;   // Pagination size
    keyword: string;    // Optional search keyword
  
    constructor(start: number = 0, pageSize: number = 10, keyword: string = '') {
      this.start = start;
      this.pageSize = pageSize;
      this.keyword = keyword;
    }
  }