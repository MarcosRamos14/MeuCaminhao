import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
                    showContent = !showContent
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)
                    Text("SwiftUI: Hello")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
        .onAppear {
            print("ContentView apareceu na tela")
            Task {
                do {
                    let repo = dependencyManager.authenticationRepostiory()
                    print(repo)
                    try await repo.authenticate(
                        email: "ybarbosap@gmail.com",
                        password: "001122"
                    )
                    print("Autenticação bem-sucedida")
                } catch {
                    print("Autenticação falhou")
                    print("Erro: \(error.localizedDescription)")
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
