export interface Principal {
    authenticated: boolean;
    authorities: Array<Authority>;
    name: string;
}

export interface Authority {
    authority: string;
}