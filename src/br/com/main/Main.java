package br.com.main;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		URI endereco = URI.create(url);
		HttpClient clientCriado = HttpClient.newHttpClient();
		HttpRequest requisicaoJson = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> resposta = clientCriado.send(requisicaoJson, BodyHandlers.ofString());
		String corpoRequisicao = resposta.body();
		
		//Tipo da chave? 'String', qual o tipo do arquivo? 'String'.
		var parser = new parseJSON();
		List<Map<String, String>> listaDeFilmes = parser.parse(corpoRequisicao);
		//Exibir os dados
		for(int i = 0; i < 6; i++) {
			Map<String, String> filme = listaDeFilmes.get(i);
			System.out.println("\n" + "\u001b[30;1m\u001b[47mTitulo do Filme\u001b[m " + "\n" + filme.get("title"));
			System.out.println(filme.get("image"));
			double imDbRating = Double.parseDouble(filme.get("imDbRating"));
			int imDbRatingConvert = (int) imDbRating;
			for(int e = 0; e < imDbRatingConvert; e++) {
				System.out.print("⭐");
			}
		}
	}

}
