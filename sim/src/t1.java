public record Book ( String title , String author , int pages ) {}
List < Book > library = List . of (
        new Book ( " Moby Dick " , " Herman Melville " , 720) ,
        new Book ( " 1984 " , " George Orwell " , 328) ,
        new Book ( " Ulysses " , " James Joyce " , 730) ,
        new Book ( " War and Peace " , " Leo Tolstoy " , 1225)
) ;
void main() {
    System.out.println(
            library.stream().filter(c -> c.pages > 500).sorted(Comparator.comparing(Book::title)).map(Book::title).toList());
}
