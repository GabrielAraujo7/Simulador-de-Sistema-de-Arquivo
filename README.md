Simulador de Sistema de Arquivos com Journaling:

Metodologia:

O simulador foi desenvolvido utilizando a linguagem de programação Java, escolhida por sua robustez e extensibilidade. Ele aceita comandos em texto, que são processados por métodos específicos com parâmetros correspondentes. Cada método simula uma funcionalidade comum em sistemas de arquivos reais, como copiar, apagar, renomear e listar arquivos e diretórios.

Além disso, foi implementado um mecanismo de journaling para registrar todas as operações realizadas, permitindo uma visão detalhada e estruturada do funcionamento do simulador. O programa exibe mensagens na tela para informar ao usuário os resultados de cada operação.

Parte 1: Introdução ao Sistema de Arquivos com Journaling:

Descrição do Sistema de Arquivos:

Um sistema de arquivos é um componente fundamental de sistemas operacionais, responsável por organizar, armazenar e recuperar dados de maneira eficiente. Ele permite que os usuários interajam com os dados armazenados em dispositivos como discos rígidos, SSDs ou pen drives.

Os sistemas de arquivos fornecem abstrações que tornam possível trabalhar com arquivos e diretórios, gerenciando permissões, hierarquias e acessos concorrentes.

Journaling:

O journaling é uma técnica utilizada em sistemas de arquivos para garantir a integridade dos dados, mesmo em situações de falha, como quedas de energia ou interrupções inesperadas. Ele registra as operações realizadas em um log antes de aplicá-las no armazenamento principal. Existem diferentes tipos de journaling, entre os quais destacam-se:

Write-Ahead Logging (WAL): Grava no log antes de realizar alterações permanentes.
Log-Structured Filesystems: Usa o log como principal método de escrita, gravando dados e metadados diretamente no registro.
Metadata Journaling: Apenas as operações relacionadas aos metadados são registradas no log.
No simulador, foi implementado um journaling simples que registra operações como criação, exclusão, cópia e renomeação de arquivos/diretórios.

Parte 2: Arquitetura do Simulador:

Estrutura de Dados:

O simulador utiliza classes Java para representar os elementos do sistema de arquivos:

Classe File: Representa arquivos individuais.
Classe Directory: Representa diretórios e gerencia subdiretórios e arquivos.
Classe FileSystemSimulator: Coordena as operações, processa comandos do usuário e interage com as classes acima.
Essas classes são implementadas de forma a refletir as propriedades e comportamentos de um sistema de arquivos real.

Journaling:
O journaling foi implementado através de um arquivo de texto (filesystem.journal), onde cada linha representa uma operação executada. Este log inclui:

Tipo de operação (e.g., copy, mkdir, rm, rename,ls).
Caminhos ou parâmetros envolvidos na operação.
Registro sequencial para permitir auditoria e recuperação de dados.

Parte 3: Implementação em Java:

Classe FileSystemSimulator:
Esta classe implementa o simulador, com métodos correspondentes aos comandos suportados:

Comandos implementados:

copy <origem> <destino>: Copia um arquivo de um local para outro.

rm <arquivo>: Remove um arquivo.

mkdir <diretório>: Cria um novo diretório.

rmdir <diretório>: Remove um diretório e seus conteúdos.

rename <antigo> <novo>: Renomeia um arquivo ou diretório.

ls <diretório>: Lista o conteúdo de um diretório.

Classes File e Directory:
Essas classes representam arquivos e diretórios no sistema de arquivos, fornecendo métodos para interagir com suas propriedades, como:

Nome.
Caminho absoluto.
Conteúdo (para arquivos).
Subdiretórios e arquivos contidos (para diretórios).

Classe Journal:
Gerencia o log de operações. Utiliza a classe BufferedWriter para registrar cada operação realizada pelo simulador. 
Esse log serve como referência para auditoria ou recuperação de estado.

