const albumsList = document.getElementById('albumsList');
const searchBar = document.getElementById('searchInput');
const allAlbums = [];
fetch("http://localhost:8080/albums/api")
    .then(response => response.json())
    .then(data => {
        for (let d of data) {
            allAlbums.push(d);
        }
    });

searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    console.log(allAlbums);
    let filteredAlbums = allAlbums.filter(album => {
        return album.name.toLowerCase().includes(searchingCharacters)
            || album.artist.name.toLowerCase().includes(searchingCharacters);
    });
    console.log(filteredAlbums);
    displayAlbums(filteredAlbums);
})


const displayAlbums = (albums) => {
    albumsList.innerHTML = albums
        .map((a) => {
            return ` <div class="col-md-3" >
                <div class="card mb-4 box-shadow">
                <img src="${a.imageUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"
                     style="height: 225px; width: 100%; display: block;">
                <div class="card-body">
                    <div class="text-center">
                        <p class="card-text border-bottom ">Name: ${a.name}</p>
                        <p class="card-text border-bottom ">Artist: ${a.artist.name}</p>
                        <p class="card-text border-bottom ">Genre: ${a.genre}</p>
                        <p class="card-text border-bottom ">Price: ${a.price}</p>
                        <p class="card-text border-bottom">Release Date: ${a.releaseDate}</p>
                    </div>
                    <div class="d-flex justify-content-between align-items-center">
                        
                        <div class="btn-group">
                            <a href="/albums/details/${a.id}"  type="button" class="btn btn-sm btn-outline-secondary">Details</a>
                        </div>
                        <div class="btn-group">
                            <a href="/albums/delete/${a.id}"  type="button" class="btn btn-sm btn-outline-secondary">Delete</a>
                        </div>
   
                    </div>
                </div>
            </div> 
            </div>`
        })
        .join('');

}


