int main(int argc, char **argv) {
	println("Start");
	

	return 0;
}
 
 
void fun_80484d0();

void fun_8048430();

void fun_80483bc() {
    int32_t ebx1;

    fun_80484d0();
    if (*reinterpret_cast<int32_t*>(ebx1 + 0x193b - 4)) {
        fun_8048430();
    }
    return;
}

void fun_80484d0() {
    return;
}

int32_t __gmon_start__ = 0x8048436;

void fun_8048430() {
    goto __gmon_start__;
}

int32_t ptrace(int32_t a1, int32_t a2);

int32_t fun_80484e0() {
    int32_t eax1;
    int32_t v2;

    eax1 = 3;
    if (!1 && (eax1 = 0, !1)) {
        eax1 = ptrace(0x8049d40, v2);
    }
    return eax1;
}

int32_t puts = 0x8048426;

void fun_8048420(int32_t a1, int32_t a2, int32_t a3, int32_t a4) {
    goto puts;
}

int32_t exit = 0x8048446;

void fun_8048440(int32_t a1, int32_t a2, int32_t a3, int32_t a4) {
    goto exit;
}

int32_t putchar = 0x8048486;

void fun_8048480(int32_t a1) {
    goto putchar;
}

int32_t strlen = 0x8048456;

uint32_t fun_8048450(void* a1, int32_t a2, int32_t a3) {
    goto strlen;
}

int32_t ptrace = 0x8048496;

int32_t fun_8048490(int32_t a1, int32_t a2, int32_t a3, int32_t a4) {
    goto ptrace;
}

int32_t strcmp = 0x80483f6;

int32_t fun_80483f0(int32_t a1, int32_t a2, int32_t a3) {
    goto strcmp;
}

int32_t printf = 0x8048406;

void fun_8048400(int32_t a1) {
    goto printf;
}

int32_t memset = 0x8048476;

void fun_8048470(int32_t a1, int32_t a2, int32_t a3) {
    goto memset;
}

int32_t fgets = 0x8048416;

int32_t fun_8048410(int32_t a1, int32_t a2, int32_t a3) {
    goto fgets;
}

int32_t __libc_start_main = 0x8048466;

void fun_8048460(int32_t a1, int32_t a2, void* a3, int32_t a4, int32_t a5, int32_t a6, void** a7, int32_t a8) {
    goto __libc_start_main;
}

void fun_8048904() {
    fun_80484d0();
    return;
}

void fun_80483ec() {
    signed char* eax1;
    signed char* eax2;
    signed char al3;
    signed char* eax4;
    signed char* eax5;
    signed char al6;

    *eax1 = reinterpret_cast<signed char>(*eax2 + al3);
    *eax4 = reinterpret_cast<signed char>(*eax5 + al6);
}

int32_t g8049c10 = 0;

int32_t fun_8048570() {
    int32_t edx1;
    int32_t v2;
    int32_t eax3;

    edx1 = g8049c10;
    if (!(!edx1 || 1)) {
        ptrace(0x8049c10, v2);
    }
    eax3 = 0;
    if (!1 && !1) {
        eax3 = ptrace(0x8049d40, 0);
    }
    return eax3;
}

void fun_804857d() {
}

void fun_804859b() {
    int32_t eax1;

    eax1 = fun_8048490(0, 0, 0, 0);
    if (eax1 == -1) {
        fun_8048420("No debugger please!", 0, 0, 0);
        fun_8048440(1, 0, 0, 0);
    }
    return;
}

void fun_80485d2() {
    void* ebp1;
    int32_t v2;
    int32_t v3;
    uint32_t eax4;
    int32_t v5;
    uint32_t eax6;
    int32_t eax7;
    uint32_t v8;
    int32_t v9;
    int32_t v10;
    uint32_t eax11;

    ebp1 = reinterpret_cast<void*>(reinterpret_cast<int32_t>(__zero_stack_offset()) - 4);
    eax4 = fun_8048450(0x8049d60, v2, v3);
    if (eax4 == 34) {
        v5 = 0;
        while (v5 <= 33) {
            eax6 = static_cast<uint32_t>(*reinterpret_cast<unsigned char*>(v5 + 0x8049d60)) ^ 51;
            eax7 = *reinterpret_cast<signed char*>(&eax6);
            if (2 != static_cast<uint32_t>(*reinterpret_cast<unsigned char*>(&eax7))) 
                goto addr_8048732_5;
            ++v5;
        }
    } else {
        goto addr_804878b_8;
    }
    v8 = 0;
    while (eax11 = fun_8048450(reinterpret_cast<int32_t>(ebp1) - 80, v9, v10), v8 < eax11) {
        fun_8048480(67);
        ++v8;
    }
    fun_8048480(10);
    addr_804878b_8:
    return;
    addr_8048732_5:
    goto addr_804878b_8;
}

void fun_80488f1() {
    return;
}

int32_t g8049d08 = 0;

void fun_8048436() {
    goto g8049d08;
}

void fun_80483f6() {
    goto 0x80483e0;
}

void fun_804850c() {
}

signed char g8049d44 = 0;

int32_t fun_8048545() {
    int1_t zf1;
    int32_t eax2;

    zf1 = g8049d44 == 0;
    if (zf1) {
        eax2 = fun_80484e0();
        g8049d44 = 1;
    }
    return eax2;
}

void fun_8048426() {
    goto 0x80483e0;
}

void fun_8048446() {
    goto 0x80483e0;
}

int32_t stdin = 0;

int32_t fun_8048790() {
    int32_t v1;
    int32_t v2;
    int32_t v3;
    int32_t eax4;
    int32_t eax5;
    uint32_t eax6;
    uint32_t eax7;
    uint32_t eax8;
    int32_t eax9;
    uint32_t eax10;
    int32_t v11;
    int32_t v12;

    fun_8048420("\n#########################################################\n### Welcome to the \"wysiNwyg\" challenge!\n###     Your task is to find out the password to be able\n###     to decrypt the password-protected zip and read\n###     the secret flag. Good Luck!!\n#########################################################\n", v1, v2, v3);
    fun_8048400("Password: ");
    fun_8048470(0x8049d60, 0, 35);
    eax4 = stdin;
    eax5 = fun_8048410(0x8049d60, 35, eax4);
    if (eax5) {
        eax6 = fun_8048450(0x8049d60, 35, eax4);
        eax7 = *reinterpret_cast<unsigned char*>(eax6 - 1 + 0x8049d60);
        if (*reinterpret_cast<signed char*>(&eax7) == 10) {
            eax8 = fun_8048450(0x8049d60, 35, eax4);
            *reinterpret_cast<unsigned char*>(eax8 - 1 + 0x8049d60) = 0;
        }
        eax9 = fun_80483f0(0x8049d60, "s3cR3t_p4sSw0rD", eax4);
        if (eax9) {
            eax10 = fun_8048450(0x8049d60, "s3cR3t_p4sSw0rD", eax4);
            if (eax10 != 34) {
                fun_8048420("Try again :(", "s3cR3t_p4sSw0rD", eax4, v11);
            }
        } else {
            fun_8048420("This is not the solution you are looking for :)", "s3cR3t_p4sSw0rD", eax4, v12);
        }
    }
    return 0;
}

void fun_8048486() {
    goto 0x80483e0;
}

void fun_8048456() {
    goto 0x80483e0;
}

void fun_8048890(int32_t a1, int32_t a2, int32_t a3) {
    int32_t edi4;
    int32_t ebx5;
    int32_t ebx6;
    int32_t ebp7;
    int32_t esi8;
    int32_t esi9;

    edi4 = 0;
    fun_80484d0();
    ebx5 = ebx6 + 0x1465;
    ebp7 = a1;
    fun_80483bc();
    esi8 = ebx5 - 0xf8 - (ebx5 - 0x100) >> 2;
    if (esi8) {
        esi9 = esi8;
        do {
            *reinterpret_cast<int32_t*>(ebx5 + edi4 * 4 - 0x100)(ebp7, a2, a3);
            ++edi4;
        } while (edi4 != esi9);
    }
    return;
}

void fun_8048496() {
    goto 0x80483e0;
}

void fun_8048406() {
    goto 0x80483e0;
}

void fun_80484a0() {
    void* esp1;
    int32_t edx2;
    int32_t eax3;

    esp1 = reinterpret_cast<void*>(reinterpret_cast<int32_t>(__zero_stack_offset()) + 4);
    fun_8048460(fun_8048790, __return_address(), esp1, fun_8048890, 0x8048900, edx2, (reinterpret_cast<uint32_t>(esp1) & 0xfffffff0) - 4 - 4, eax3);
    __asm__("hlt ");
}

void fun_8048476() {
    goto 0x80483e0;
}

void fun_8048416() {
    goto 0x80483e0;
}

void fun_8048466() {
    goto 0x80483e0;
}
