
---

### 📌 **README.md** (Copie e cole no seu projeto)

```md
# 📚 Sistema de Gerenciamento de Biblioteca

![GitHub repo size](https://img.shields.io/github/repo-size/SEU_USUARIO/SEU_REPOSITORIO)
![GitHub contributors](https://img.shields.io/github/contributors/SEU_USUARIO/SEU_REPOSITORIO)
![GitHub last commit](https://img.shields.io/github/last-commit/SEU_USUARIO/SEU_REPOSITORIO)
![GitHub license](https://img.shields.io/github/license/SEU_USUARIO/SEU_REPOSITORIO)

🔹 Um sistema completo para **gerenciar bibliotecas**, permitindo cadastro de livros, usuários, empréstimos, devoluções e aplicação de multas de forma dinâmica.

---

## ✨ **Funcionalidades**
✅ Registro de usuários.  
✅ Adição e remoção de itens da biblioteca (Livros e Revistas).  
✅ Empréstimos e devoluções de itens.  
✅ Cálculo de multas para devoluções atrasadas.  
✅ Estratégias de multa configuráveis (_Multa Padrão_ e _Multa Progressiva_).  
✅ Notificações automáticas para usuários via **Observer Pattern**.  

---

## 🛠 **Tecnologias Utilizadas**
- **Java 17+** ☕
- **Padrões de Projeto GoF:**
  - 🏭 **Factory Method** → Criação dinâmica de livros e revistas.
  - 🔄 **Strategy** → Estratégias de cálculo de multas.
  - 👀 **Observer** → Notificações automáticas para usuários.
  - 🔒 **Singleton** → Gerenciamento centralizado da biblioteca.
- **Paradigma Orientado a Objetos (POO)** 🎯
- **Boas práticas de separação de responsabilidades** ✅

---

## 📂 **Estrutura do Projeto**
```
/bibliotecaPDS
│── src/
│   ├── factory/
│   │   ├── ItemBibliotecaFactory.java
│   ├── model/
│   │   ├── ItemBiblioteca.java
│   │   ├── Livro.java
│   │   ├── Revista.java
│   │   ├── Usuario.java
│   ├── observer/
│   │   ├── Notificador.java
│   ├── strategy/
│   │   ├── IMultaStrategy.java
│   │   ├── MultaPadrao.java
│   │   ├── MultaProgressiva.java
│   ├── Biblioteca.java
│   ├── Main.java
│── README.md
│── .gitignore
│── bibliotecaPDS.iml
```

---

## 🚀 **Como Executar**
### **Pré-requisitos**
📌 Certifique-se de ter **Java 17+** instalado em sua máquina.

### **Passo 1: Clonar o Repositório**
```bash
git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git
cd SEU_REPOSITORIO
```

### **Passo 2: Compilar e Executar**
```bash
javac -d bin src/*.java
java -cp bin Main
```

---

## 🔍 **Como Usar**
Após iniciar o sistema, você verá o seguinte menu:

```
--- Menu da Biblioteca ---
1. Registrar novo usuário
2. Adicionar novo item
3. Emprestar item
4. Devolver item (verifica atraso e aplica multa)
5. Notificar usuários
6. Listar itens cadastrados
7. Listar usuários
0. Sair
```

➡ Para devolver um item, o sistema perguntará **"Houve atraso?"**  
➡ Se houver atraso, ele pedirá para escolher entre:  
   - **Multa Padrão** (R$ 1,00 por dia).  
   - **Multa Progressiva** (R$ 0,50 * dias²).  

---

## 🎯 **Exemplo de Uso**
### 🔹 **Emprestar um Livro**
1. **Adicione um usuário**
2. **Adicione um livro** (exemplo: "O Senhor dos Anéis").
3. **Empreste o livro ao usuário.**
4. **Devolva o livro** e veja a aplicação de multas em caso de atraso.

```
📚 Livro emprestado: O Senhor dos Anéis para João
⌛ Devolvendo livro...
Houve atraso? (s/n): s
Escolha a estratégia de multa:
1. Multa Padrão (R$ 1,00 por dia)
2. Multa Progressiva (R$ 0,50 * dias²)
Opção: 2
Multa aplicada: R$ 4,50
✔ Livro devolvido com sucesso!
```

---

## 📜 **Licença**
📌 Este projeto está sob a licença **MIT** – sinta-se livre para usá-lo e melhorá-lo!


```