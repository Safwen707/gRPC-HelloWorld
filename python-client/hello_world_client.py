# Python gRPC client for the MultilingualGreeter service
import grpc
import logging
import time
import sys
import argparse
from concurrent import futures

# These imports will work after running the protoc compiler to generate the Python files
# from the .proto file (will be explained in the README)
import hello_service_pb2
import hello_service_pb2_grpc

def run_unary_call(stub, first_name, last_name, lang_code):
    """Make a simple unary RPC call"""
    print(f"Making unary call with {first_name} {last_name} in language {lang_code}")
    
    request = hello_service_pb2.HelloRequest(
        first_name=first_name,
        last_name=last_name,
        language_code=lang_code
    )
    
    try:
        response = stub.SayHello(request)
        print(f"Server response: {response.greeting}")
    except grpc.RpcError as e:
        print(f"RPC failed: {e}")

def run_server_streaming(stub, first_name, last_name, lang_code):
    """Make a server streaming RPC call"""
    print(f"Making server streaming call with {first_name} {last_name}")
    
    request = hello_service_pb2.HelloRequest(
        first_name=first_name,
        last_name=last_name,
        language_code=lang_code
    )
    
    try:
        responses = stub.SayHellosServerStreaming(request)
        for response in responses:
            print(f"Server streaming response: {response.greeting}")
    except grpc.RpcError as e:
        print(f"RPC failed: {e}")

def run_client_streaming(stub):
    """Make a client streaming RPC call"""
    print("Vous allez envoyer plusieurs salutations (streaming client)")
    print("Entrez les informations pour plusieurs personnes. Tapez 'fin' comme prénom pour terminer.")
    
    people = []
    
    # Collecter les données des personnes
    while True:
        first_name = input("\nPrénom (ou 'fin' pour terminer): ")
        if first_name.lower() == 'fin':
            break
            
        last_name = input("Nom: ")
        
        print("Choisissez une langue:")
        print("en - Anglais")
        print("fr - Français")
        print("ar - Arabe")
        lang_code = input("Code de langue (en/fr/ar): ")
        
        people.append((first_name, last_name, lang_code))
        print(f"Personne ajoutée: {first_name} {last_name} ({lang_code})")
    
    if not people:
        print("Aucune personne ajoutée. Opération annulée.")
        return
    
    # Generator function for sending multiple requests
    def request_generator():
        for first_name, last_name, lang_code in people:
            request = hello_service_pb2.HelloRequest(
                first_name=first_name,
                last_name=last_name,
                language_code=lang_code
            )
            print(f"Envoi de la demande pour {first_name} {last_name}...")
            yield request
            time.sleep(0.2)  # Add a small delay between requests
    
    try:
        print("Envoi des demandes au serveur...")
        response = stub.SayHellosClientStreaming(request_generator())
        print(f"Réponse du streaming client: {response.greeting}")
    except grpc.RpcError as e:
        print(f"Échec de l'appel RPC: {e}")

def run_bidirectional_streaming(stub):
    """Make a bidirectional streaming RPC call"""
    print("Vous allez démarrer un streaming bidirectionnel")
    print("Entrez les informations pour plusieurs personnes. Tapez 'fin' comme prénom pour terminer.")
    
    people = []
    
    # Collecter les données des personnes
    while True:
        first_name = input("\nPrénom (ou 'fin' pour terminer): ")
        if first_name.lower() == 'fin':
            break
            
        last_name = input("Nom: ")
        
        print("Choisissez une langue:")
        print("en - Anglais")
        print("fr - Français")
        print("ar - Arabe")
        lang_code = input("Code de langue (en/fr/ar): ")
        
        people.append((first_name, last_name, lang_code))
        print(f"Personne ajoutée: {first_name} {last_name} ({lang_code})")
    
    if not people:
        print("Aucune personne ajoutée. Opération annulée.")
        return
    
    # Generator function for sending multiple requests
    def request_generator():
        for first_name, last_name, lang_code in people:
            request = hello_service_pb2.HelloRequest(
                first_name=first_name,
                last_name=last_name,
                language_code=lang_code
            )
            print(f"Envoi de la demande pour {first_name} {last_name}...")
            yield request
            time.sleep(0.3)  # Add a small delay between requests
    
    try:
        print("Démarrage du streaming bidirectionnel...")
        responses = stub.SayHellosBidirectional(request_generator())
        for response in responses:
            print(f"Réponse du streaming bidirectionnel: {response.greeting}")
    except grpc.RpcError as e:
        print(f"Échec de l'appel RPC: {e}")

def run():
    parser = argparse.ArgumentParser(description='gRPC Python client for MultilingualGreeter')
    parser.add_argument('--target', default='localhost:50051', help='Server address in format host:port')
    args = parser.parse_args()
    
    # Create a gRPC channel
    with grpc.insecure_channel(args.target) as channel:
        stub = hello_service_pb2_grpc.MultilingualGreeterStub(channel)
        
        while True:
            print("\n=== Menu gRPC Client Multilingue ===")
            print("1. Appel simple (Unary RPC)")
            print("2. Streaming Serveur (Server Streaming RPC)")
            print("3. Streaming Client (Client Streaming RPC)")
            print("4. Streaming Bidirectionnel (Bidirectional Streaming RPC)")
            print("5. Quitter")
            
            choice = input("\nChoisissez une option (1-5): ")
            
            if choice == "1":
                # Appel simple (Unary RPC)
                print("\n=== Appel Unary RPC ===")
                first_name = input("Entrez votre prénom: ")
                last_name = input("Entrez votre nom: ")
                print("Choisissez une langue:")
                print("en - Anglais")
                print("fr - Français")
                print("ar - Arabe")
                lang_code = input("Code de langue (en/fr/ar): ")
                run_unary_call(stub, first_name, last_name, lang_code)
                
            elif choice == "2":
                # Streaming Serveur
                print("\n=== Server Streaming RPC ===")
                first_name = input("Entrez votre prénom: ")
                last_name = input("Entrez votre nom: ")
                print("Choisissez une langue:")
                print("en - Anglais")
                print("fr - Français")
                print("ar - Arabe")
                lang_code = input("Code de langue (en/fr/ar): ")
                run_server_streaming(stub, first_name, last_name, lang_code)
                
            elif choice == "3":
                # Streaming Client
                print("\n=== Client Streaming RPC ===")
                run_client_streaming(stub)
                
            elif choice == "4":
                # Streaming Bidirectionnel
                print("\n=== Bidirectional Streaming RPC ===")
                run_bidirectional_streaming(stub)
                
            elif choice == "5":
                print("Au revoir!")
                break
                
            else:
                print("Option invalide. Veuillez réessayer.")

if __name__ == '__main__':
    logging.basicConfig(level=logging.INFO)
    run()