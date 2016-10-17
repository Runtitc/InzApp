# InzApp
postgres:
baza danych: tablica przechowywujaca kolumny:
				id, login, hasło (zaszyfrowane), data ostatniego logowania
				tabela osttanie zapisne logi: id logu, sciezka na serwerze do pliku z logami, data zapisania logu
				tabelka ostatnie konfiguracje - id konfiguracji, adres karty do podsłuchu, {opcje do opracowania}
				adresy ip kart które chcemy monitorować
				
aplikacja:
			unless snort is installed
				okienko o koniecznosci zainstalowania snorta + help
				
			else
				wykryta wersja sorta - aktualizacja czy nie
				
				widok 1:
					uruchomienie aplikacji -> system logowania
					okienko : login hasło, przycisk (2x textfield, button, label ze trzeba sie zalogowac)
					menu: zamknij, info o programie, rejestracja
					
				po walidacji widok 2:
					karty konfiguracja, monitoring
					paginator - do logów
					konifguracja:
									po lewej strony opcje i checkboxy (opcje zwiazane ze snortem)
									przycisk zapisz ustawienia
					monitoring:
									textfield z logami ze snorta
									
				widok 3: rejestracja - standard				
								
								
